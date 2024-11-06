// Generated by view binder compiler. Do not edit!
package com.appstory.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.appstory.R;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddStoryBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final Button cameraButton;

  @NonNull
  public final TextInputEditText etDescription;

  @NonNull
  public final Button galleryButton;

  @NonNull
  public final SwitchMaterial locationSwitch;

  @NonNull
  public final ScrollView main;

  @NonNull
  public final ImageView previewImageView;

  @NonNull
  public final LinearProgressIndicator progressIndicator;

  @NonNull
  public final TextInputLayout textInputLayout;

  @NonNull
  public final Button uploadButton;

  private ActivityAddStoryBinding(@NonNull ScrollView rootView, @NonNull Button cameraButton,
      @NonNull TextInputEditText etDescription, @NonNull Button galleryButton,
      @NonNull SwitchMaterial locationSwitch, @NonNull ScrollView main,
      @NonNull ImageView previewImageView, @NonNull LinearProgressIndicator progressIndicator,
      @NonNull TextInputLayout textInputLayout, @NonNull Button uploadButton) {
    this.rootView = rootView;
    this.cameraButton = cameraButton;
    this.etDescription = etDescription;
    this.galleryButton = galleryButton;
    this.locationSwitch = locationSwitch;
    this.main = main;
    this.previewImageView = previewImageView;
    this.progressIndicator = progressIndicator;
    this.textInputLayout = textInputLayout;
    this.uploadButton = uploadButton;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddStoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddStoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_story, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddStoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cameraButton;
      Button cameraButton = ViewBindings.findChildViewById(rootView, id);
      if (cameraButton == null) {
        break missingId;
      }

      id = R.id.etDescription;
      TextInputEditText etDescription = ViewBindings.findChildViewById(rootView, id);
      if (etDescription == null) {
        break missingId;
      }

      id = R.id.galleryButton;
      Button galleryButton = ViewBindings.findChildViewById(rootView, id);
      if (galleryButton == null) {
        break missingId;
      }

      id = R.id.locationSwitch;
      SwitchMaterial locationSwitch = ViewBindings.findChildViewById(rootView, id);
      if (locationSwitch == null) {
        break missingId;
      }

      ScrollView main = (ScrollView) rootView;

      id = R.id.previewImageView;
      ImageView previewImageView = ViewBindings.findChildViewById(rootView, id);
      if (previewImageView == null) {
        break missingId;
      }

      id = R.id.progressIndicator;
      LinearProgressIndicator progressIndicator = ViewBindings.findChildViewById(rootView, id);
      if (progressIndicator == null) {
        break missingId;
      }

      id = R.id.textInputLayout;
      TextInputLayout textInputLayout = ViewBindings.findChildViewById(rootView, id);
      if (textInputLayout == null) {
        break missingId;
      }

      id = R.id.uploadButton;
      Button uploadButton = ViewBindings.findChildViewById(rootView, id);
      if (uploadButton == null) {
        break missingId;
      }

      return new ActivityAddStoryBinding((ScrollView) rootView, cameraButton, etDescription,
          galleryButton, locationSwitch, main, previewImageView, progressIndicator, textInputLayout,
          uploadButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
