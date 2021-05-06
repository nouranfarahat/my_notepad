<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:background="@drawable/header"
    >

    <LinearLayout
        android:id="@+id/header"
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:background="@drawable/header"
        android:orientation="vertical">


        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:text="Create an account"
            android:textColor="@color/logo_background"
            android:textSize="30dp"
            android:layout_marginTop="15dp"
            android:fontFamily="sans-serif-thin"
            />


    </LinearLayout>
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@+id/header"
        android:orientation="vertical"
        android:background="@color/logo_background"
        android:padding="5dp"
        android:layout_marginTop="11dp"
        android:layout_marginLeft="10dp"
        android:layout_marginRight="10dp"
        android:layout_marginBottom="10dp"
        android:gravity="center_horizontal">

        <com.google.android.material.textfield.TextInputLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:errorEnabled="false"
            app:counterEnabled="true"
            android:id="@+id/et_username">

            <com.google.android.material.textfield.TextInputEditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:drawableLeft="@drawable/person"
                android:hint="Username"
                android:inputType="text"/>

        </com.google.android.material.textfield.TextInputLayout>
        <com.google.android.material.textfield.TextInputLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:errorEnabled="false"
            app:counterEnabled="true"
            android:id="@+id/et_email">

            <com.google.android.material.textfield.TextInputEditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:drawableLeft="@drawable/ic_baseline_email_24"
                android:hint="Email"
                android:inputType="text"/>

        </com.google.android.material.textfield.TextInputLayout>
        <com.google.android.material.textfield.TextInputLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:errorEnabled="false"
            app:counterEnabled="true"
            app:passwordToggleEnabled="true"
            android:id="@+id/et_password">

            <com.google.android.material.textfield.TextInputEditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:drawableStart="@drawable/password"
                android:hint="Password"
                android:inputType="numberPassword"/>

        </com.google.android.material.textfield.TextInputLayout>

        <com.google.android.material.textfield.TextInputLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:errorEnabled="false"
            app:counterEnabled="true"
            app:passwordToggleEnabled="true"
            android:id="@+id/et_password2">

            <com.google.android.material.textfield.TextInputEditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:drawableStart="@drawable/password"
                android:hint="Confirm Password"
                android:inputType="numberPassword"/>

        </com.google.android.material.textfield.TextInputLayout>
        <com.google.android.material.textfield.TextInputLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:errorEnabled="false"
            app:counterEnabled="true"
            app:passwordToggleEnabled="true"
            android:id="@+id/et_phone">

            <com.google.android.material.textfield.TextInputEditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:drawableStart="@drawable/ic_baseline_phone_24"
                android:hint="Phone"
                android:inputType="number"/>

        </com.google.android.material.textfield.TextInputLayout>

        <Button
            android:id="@+id/btn_register"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Sign up"
            android:background="@drawable/button_style"
            