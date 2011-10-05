package org.exoplatform.ui;

import greendroid.widget.ActionBarItem;

import org.exoplatform.R;
import org.exoplatform.controller.dashboard.DashboardController;
import org.exoplatform.singleton.LocalizationHelper;
import org.exoplatform.widget.MyActionBar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DashboardActivity extends MyActionBar {

  private DashboardController     controller;

  private ListView                listView;

  private View                    empty_stub;

  private String                  title;

  private String                  dashboardEmptyString;

  public static DashboardActivity dashboardActivity;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setActionBarContentView(R.layout.dashboard_layout);
    changeLanguage();
    dashboardActivity = this;
    getActionBar().setType(greendroid.widget.ActionBar.Type.Normal);

    listView = (ListView) findViewById(R.id.dashboard_listview);
    listView.setCacheColorHint(Color.TRANSPARENT);
    listView.setFadingEdgeLength(0);

    controller = new DashboardController(this, listView);
    controller.onLoad();
  }

  public void setEmptyView(int status) {
    if (empty_stub == null) {
      initStubView();
    }
    empty_stub.setVisibility(status);

  }

  private void initStubView() {
    empty_stub = ((ViewStub) findViewById(R.id.dashboard_empty_stub)).inflate();
    ImageView emptyImage = (ImageView) empty_stub.findViewById(R.id.empty_image);
    emptyImage.setBackgroundResource(R.drawable.icon_for_no_gadgets);
    TextView emptyStatus = (TextView) empty_stub.findViewById(R.id.empty_status);
    emptyStatus.setText(dashboardEmptyString);
  }

  @Override
  public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
    switch (position) {

    case -1:
      finish();
      break;
    case 0:

      break;

    default:

    }
    return true;

  }

  @Override
  public void onBackPressed() {
    controller.onCancelLoad();
    finish();
  }

  private void changeLanguage() {
    LocalizationHelper local = LocalizationHelper.getInstance();
    title = local.getString("Dashboard");
    setTitle(title);
    dashboardEmptyString = local.getString("EmptyDashboard");
  }

}