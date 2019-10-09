package com.colin.demo.data_binding;

import android.view.View;
import android.widget.Toast;

public class OnClickHandler {
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "onClicked", Toast.LENGTH_SHORT).show();
    }
}
