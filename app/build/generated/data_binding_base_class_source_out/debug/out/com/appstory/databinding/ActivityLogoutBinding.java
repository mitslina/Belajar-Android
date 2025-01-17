// Generated by view binder compiler. Do not edit!
package com.appstory.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.appstory.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLogoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Guideline guidelineHorizontal;

  @NonNull
  public final Guideline guidelineHorizontal3;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final Button logoutButton;

  @NonNull
  public final TextView messageTextView;

  @NonNull
  public final TextView nameTextView;

  private ActivityLogoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull Guideline guidelineHorizontal, @NonNull Guideline guidelineHorizontal3,
      @NonNull ImageView imageView, @NonNull Button logoutButton, @NonNull TextView messageTextView,
      @NonNull TextView nameTextView) {
    this.rootView = rootView;
    this.guidelineHorizontal = guidelineHorizontal;
    this.guidelineHorizontal3 = guidelineHorizontal3;
    this.imageView = imageView;
    this.logoutButton = logoutButton;
    this.messageTextView = messageTextView;
    this.nameTextView = nameTextView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLogoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLogoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_logout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLogoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.guidelineHorizontal;
      Guideline guidelineHorizontal = ViewBindings.findChildViewById(rootView, id);
      if (guidelineHorizontal == null) {
        break missingId;
      }

      id = R.id.guidelineHorizontal3;
      Guideline guidelineHorizontal3 = ViewBindings.findChildViewById(rootView, id);
      if (guidelineHorizontal3 == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.logoutButton;
      Button logoutButton = ViewBindings.findChildViewById(rootView, id);
      if (logoutButton == null) {
        break missingId;
      }

      id = R.id.messageTextView;
      TextView messageTextView = ViewBindings.findChildViewById(rootView, id);
      if (messageTextView == null) {
        break missingId;
      }

      id = R.id.nameTextView;
      TextView nameTextView = ViewBindings.findChildViewById(rootView, id);
      if (nameTextView == null) {
        break missingId;
      }

      return new ActivityLogoutBinding((ConstraintLayout) rootView, guidelineHorizontal,
          guidelineHorizontal3, imageView, logoutButton, messageTextView, nameTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
