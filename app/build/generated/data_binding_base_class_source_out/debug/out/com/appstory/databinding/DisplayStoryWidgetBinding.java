// Generated by view binder compiler. Do not edit!
package com.appstory.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.appstory.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DisplayStoryWidgetBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button btnClick;

  @NonNull
  public final ImageView imageView;

  private DisplayStoryWidgetBinding(@NonNull RelativeLayout rootView, @NonNull Button btnClick,
      @NonNull ImageView imageView) {
    this.rootView = rootView;
    this.btnClick = btnClick;
    this.imageView = imageView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DisplayStoryWidgetBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DisplayStoryWidgetBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.display_story_widget, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DisplayStoryWidgetBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_click;
      Button btnClick = ViewBindings.findChildViewById(rootView, id);
      if (btnClick == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      return new DisplayStoryWidgetBinding((RelativeLayout) rootView, btnClick, imageView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
