// Generated by view binder compiler. Do not edit!
package com.appstory.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.appstory.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDetailStoryBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final ImageView ivDetailPhoto;

  @NonNull
  public final ScrollView main;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final TextView tvDetailCreatedAt;

  @NonNull
  public final TextView tvDetailDescription;

  @NonNull
  public final TextView tvDetailName;

  private ActivityDetailStoryBinding(@NonNull ScrollView rootView, @NonNull ImageView ivDetailPhoto,
      @NonNull ScrollView main, @NonNull ProgressBar progressBar,
      @NonNull TextView tvDetailCreatedAt, @NonNull TextView tvDetailDescription,
      @NonNull TextView tvDetailName) {
    this.rootView = rootView;
    this.ivDetailPhoto = ivDetailPhoto;
    this.main = main;
    this.progressBar = progressBar;
    this.tvDetailCreatedAt = tvDetailCreatedAt;
    this.tvDetailDescription = tvDetailDescription;
    this.tvDetailName = tvDetailName;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetailStoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetailStoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_detail_story, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetailStoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.iv_detail_photo;
      ImageView ivDetailPhoto = ViewBindings.findChildViewById(rootView, id);
      if (ivDetailPhoto == null) {
        break missingId;
      }

      ScrollView main = (ScrollView) rootView;

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.tv_detail_created_at;
      TextView tvDetailCreatedAt = ViewBindings.findChildViewById(rootView, id);
      if (tvDetailCreatedAt == null) {
        break missingId;
      }

      id = R.id.tv_detail_description;
      TextView tvDetailDescription = ViewBindings.findChildViewById(rootView, id);
      if (tvDetailDescription == null) {
        break missingId;
      }

      id = R.id.tv_detail_name;
      TextView tvDetailName = ViewBindings.findChildViewById(rootView, id);
      if (tvDetailName == null) {
        break missingId;
      }

      return new ActivityDetailStoryBinding((ScrollView) rootView, ivDetailPhoto, main, progressBar,
          tvDetailCreatedAt, tvDetailDescription, tvDetailName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
