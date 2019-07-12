package com.example.opencameramodule.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.opencameramodule.R;
import com.example.opencameramodule.databinding.ItemChoiceBinding;
import com.example.opencameramodule.databinding.ItemCommentBinding;
import com.example.opencameramodule.databinding.ItemPhotoBinding;
import com.example.opencameramodule.view.AppValues;
import com.example.opencameramodule.view.SingleSelectType;
import com.example.opencameramodule.view.SingleTypeOptionSelectedListener;
import com.example.opencameramodule.view.model.Parent;
import com.example.opencameramodule.view.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Parent> parents = new ArrayList<>();
    Context context;
    MainActivityViewModel viewModel;

    public MainRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Parent> parents) {
        this.parents.addAll(parents);
        notifyDataSetChanged();
    }

    public void setViewModel(MainActivityViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == AppValues.CardTypes.COMMENT) {
            ItemCommentBinding itemCommentBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.item_comment, parent, false);
            return new CommentViewHolder(itemCommentBinding);
        } else if (viewType == AppValues.CardTypes.PHOTO) {
            ItemPhotoBinding itemPhotoBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.item_photo, parent, false);
            return new PhotoViewHolder(itemPhotoBinding);
        } else if (viewType == AppValues.CardTypes.SINGLE_CHOICE) {
            ItemChoiceBinding itemChoiceBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()), R.layout.item_choice, parent, false);
            return new ChoiceViewHolder(itemChoiceBinding);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == AppValues.CardTypes.COMMENT) {
            Parent parent = parents.get(position);
            ((CommentViewHolder) holder).bind(parent);
        } else if (holder.getItemViewType() == AppValues.CardTypes.PHOTO) {
            Parent parent = parents.get(position);
            ((PhotoViewHolder) holder).bind(parent);
        } else if (holder.getItemViewType() == AppValues.CardTypes.SINGLE_CHOICE) {
            ((ChoiceViewHolder) holder).binding.itemChoiceOptionsLayout.removeAllViews();
            SingleSelectType singleSelectAnswersTypeOptions = new SingleSelectType.Builder()
                    .setContext(context)
                    .setPreviousSelectedId(parents.get(position).getStringMap().values().toString())
                    .setOptionList(parents.get(position))
                    .setListener(onClickListener)
                    .buildOption();

            ((ChoiceViewHolder) holder).binding.itemChoiceOptionsLayout.addView(singleSelectAnswersTypeOptions);

            Parent parent = parents.get(position);
            ((ChoiceViewHolder) holder).bind(parent);
        }
    }

    @Override
    public int getItemCount() {
        return parents.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (parents.size() == 0) {
            return AppValues.CardTypes.NO_TYPE;
        } else {
            if (AppValues.CardTypeValues.COMMENT.equals(parents.get(position).getType())) {
                return AppValues.CardTypes.COMMENT;
            } else if (AppValues.CardTypeValues.SINGLE_CHOICE.equals(parents.get(position).getType())) {
                return AppValues.CardTypes.SINGLE_CHOICE;
            } else if (AppValues.CardTypeValues.PHOTO.equals(parents.get(position).getType())) {
                return AppValues.CardTypes.PHOTO;
            }
        }
        return AppValues.CardTypes.NO_TYPE;
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private ItemCommentBinding binding;

        public CommentViewHolder(@NonNull ItemCommentBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(Parent parent) {
            binding.setItem(parent);
            binding.executePendingBindings();
            binding.itemCommentEdittext.setText(parent.getStringMap().get(parent.getId()));
            binding.itemCommentEdittext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    //Do nothing
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    parent.getStringMap().put(parent.getId(), s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                    //Do nothing
                }
            });
            if (parent.getIsEnabled() != null && parent.getIsEnabled()) {
                binding.setEditTextEnable(true);
            } else {
                binding.setEditTextEnable(false);
            }
            binding.itemCommentSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        parent.setIsEnabled(true);
                        binding.setEditTextEnable(true);
                    } else {
                        parent.setIsEnabled(false);
                        commentSaveToDB();
                        binding.setEditTextEnable(false);
                    }
                }

                private void commentSaveToDB() {
                    Map<String, String> stringMap = new HashMap<>();
                    stringMap.put(parents.get(getAdapterPosition()).getId(), binding.
                            itemCommentEdittext.getText().toString());
                    viewModel.saveDataToDB(stringMap);
                }
            });
        }
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ItemPhotoBinding binding;

        public PhotoViewHolder(@NonNull ItemPhotoBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(Parent parent) {
            binding.setItem(parent);
            binding.executePendingBindings();
            if (parent.getBitmap() != null) {
                binding.itemPhotoImage.setImageBitmap(parent.getBitmap());
                binding.setRemoveButtonEnable(true);
            }
            binding.itemPhotoImage.setOnClickListener(v -> {
                if (parent.getIsEnabled()) {
                    //Todo enlarge image
                } else {
                    dispatchTakePictureIntent();
                    AppValues.Constants.ITEM_POS = getAdapterPosition();
                    binding.setRemoveButtonEnable(true);
                }
            });
            binding.itemPhotoCancel.setOnClickListener(v -> {
                if (parent.getBitmap() != null){
                    parent.setBitmap(null);
                    parent.setIsEnabled(false);
                    viewModel.removeDataById(parent.getId());
                }
                notifyItemChanged(getAdapterPosition());
            });
        }
    }

    public class ChoiceViewHolder extends RecyclerView.ViewHolder {
        private ItemChoiceBinding binding;

        public ChoiceViewHolder(@NonNull ItemChoiceBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(Parent parent) {
            binding.setItem(parent);
            binding.executePendingBindings();
        }
    }

    SingleTypeOptionSelectedListener onClickListener = new SingleTypeOptionSelectedListener() {
        @Override
        public void onSingleSelected(Map<String, String> selectedItem) {
            viewModel.saveDataToDB(selectedItem);
        }
    };

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            ((Activity) context).startActivityForResult(takePictureIntent, AppValues.Constants.REQUEST_IMAGE_CAPTURE);
        }
    }

    public void setImage(Bitmap image, int pos) {
        if (parents.size() != 0) {
            parents.get(pos).setBitmap(image);
            parents.get(pos).setIsEnabled(true);
            notifyItemChanged(pos);
        }
        setToDB(pos, image);
    }

    private void setToDB(int pos, Bitmap image) {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put(parents.get(pos).getId(), image.toString());
        viewModel.saveDataToDB(stringMap);
    }
}
