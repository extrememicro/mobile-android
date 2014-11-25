/*
 * Copyright (C) 2003-2014 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.accountswitcher;

import org.exoplatform.R;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by The eXo Platform SAS
 * Author : Philippe Aristote
 *          paristote@exoplatform.com
 * Sep 3, 2014  
 */
public class AccountSwitcherActivity extends FragmentActivity {
  
  private static final String TAG = "eXo____AccountSwitcherActivity____";

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    boolean isDialog = false;
    // Detect the size of the screen and set a theme "Dialog" to display the activity as a dialog
    // if the screen is LARGE or XLARGE
    // TODO find how to set the black background translucent
    int screenLayout = getResources().getConfiguration().screenLayout;
    screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;
    if (screenLayout == Configuration.SCREENLAYOUT_SIZE_LARGE ||
        screenLayout == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
      setTheme(R.style.Theme_eXo_Dialog);
      isDialog = true;
    }
    Log.i(TAG, "Start account switcher in mode: "+(isDialog ? "dialog" : "activity"));
    
    setContentView(R.layout.account_switcher_activity);

    getSupportFragmentManager()
     .beginTransaction()
     .add(R.id.fragment_panel, new AccountSwitcherFragment(), AccountSwitcherFragment.FRAGMENT_TAG)
     .commit();
  }
  
}
