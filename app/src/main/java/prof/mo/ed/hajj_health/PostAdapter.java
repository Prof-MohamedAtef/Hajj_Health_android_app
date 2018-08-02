package prof.mo.ed.hajj_health;

import android.*;
import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import mo.ed.prof.onsinai.Adapter.CustomSpinnerAdapter;


/**
 * Created by hayyan2001 on 11/21/16.
 */
public class PostAdapter extends ArrayAdapter {
    PostHolder postHolder;
    public transient Context mContext;
    private LayoutInflater inflater = null;
    private List<OptionsEntity> feedItemList;
    DBHelper dbHelper;
    String getProfilePicture;
    String getLatitude;
    String getLongitude;
    String getLocation;
    String getEmail;
    String getMobile;
    AlertDialog alertDialog;
    String getFaceBookUri, getFaceBookUri2;

    public PostAdapter(Context context, int Resource, ArrayList<OptionsEntity> feedItemList) {
        super(context, Resource, feedItemList);
        mContext = context;
        this.feedItemList = feedItemList;
        dbHelper = new DBHelper(context);
    }

    @Override
    public int getCount() {
        return feedItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return feedItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private void CallPermissionAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage(mContext.getString(android.R.string.CallPermissionAlert))
                .setCancelable(false)
                .setPositiveButton(mContext.getString(android.R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getMobile));
                        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }else{
                            mContext.startActivity(intent);
                        }
                    }
                })
                .setNegativeButton(mContext.getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
//                        buildAlertMessageNoGpsWarning();
                        dialog.dismiss();

                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void LaunchQuickReachView(){
        getUserName=feedItemList.get(position_launch).getUserName();
        getProfilePicture=feedItemList.get(position_launch).getProfile_Images();
        getLatitude=feedItemList.get(position_launch).getLatitude();
        getLongitude=feedItemList.get(position_launch).getLongitude();
        getLocation=feedItemList.get(position_launch).getAddress();
        getEmail=feedItemList.get(position_launch).getEmail();
        getMobile=feedItemList.get(position_launch).getMobile1();
        getFaceBookUri=feedItemList.get(position_launch).getFacebookID();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialougeView  = inflater.inflate(android.R.layout.profile_view_layout,null);
        CircleImageView profilePic = (CircleImageView)dialougeView.findViewById(android.R.id.profile_profilePic);
        TextView profile_username = (TextView)dialougeView.findViewById(android.R.id.profile_usename);
        TextView profile_distance = (TextView)dialougeView.findViewById(android.R.id.profile_distance);
        TextView Profile_email = (TextView)dialougeView.findViewById(android.R.id.profile_email);
        TextView profile_mobile = (TextView)dialougeView.findViewById(android.R.id.profile_mobile);
        ImageView cancel = (ImageView)dialougeView.findViewById(android.R.id.close);
        profilePic.setImageURI(Uri.parse(getProfilePicture));
        profile_username.setText(getUserName);
        profile_distance.setText("Distance: "+ getLocation);
        Profile_email.setText("E-Mail: " + getEmail);
        profile_mobile.setText("MobileNumber: "+ getMobile);
        if (getMobile!=null){
            builder.setView(dialougeView).setPositiveButton(mContext.getString(android.R.string.call), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) mContext, Manifest.permission.CALL_PHONE)) {
                            // Show an explanation to the user *asynchronously* -- don't block
                            // this thread waiting for the user's response! After the user
                            // sees the explanation, try again to request the permission.
                            CallPermissionAlert();
                            Toast.makeText(getContext(),"Should show an explanation", Toast.LENGTH_LONG).show();
                        } else {
                            // No explanation needed, we can request the permission.
                            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE}, postFragment.MY_PERMISSIONS_REQUEST_CALL_PHONE);
                            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                            Toast.makeText(getContext(),"Request an explanation", Toast.LENGTH_LONG).show();
                        }
                    }else if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getMobile));
                                    mContext.startActivity(intent);
                    }
                }
            });
        }
                    /*
                    gmail
                     */
        builder.setView(dialougeView).setNeutralButton(mContext.getString(android.R.string.email),  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, getUserName +" via gerany");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                intent.setData(Uri.parse("mailto:"+getEmail)); // or just "mailto:" for blank
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                mContext.startActivity(intent);
            }
        });
                    /*
                    facebook messenger
                     */
        builder.setView(dialougeView).setNegativeButton(mContext.getString(android.R.string.messenger),  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
//                                    Uri uri = Uri.parse("fb-messenger://user/");
//                                    Uri uri = Uri.parse("https://www.facebook.com/messages/t/noha.mohammed.988");
//                getFaceBookUri="https://www.facebook.com/messages/t/100005789956218";
//                getFaceBookUri2="https://www.facebook.com/messages/t/noha.mohammed.988";
//                if (getFaceBookUri!=null){
//
//                }
                Uri uri = Uri.parse("fb://messaging/100005789956218");
//                Uri uri = Uri.parse("fb://messages/t/noha.mohammed.988");
//                                    uri = ContentUris.withAppendedId(uri,Long.valueOf("noha.mohammed.988"));
                Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri); // it's not ACTION_SEND
//                                    sendIntent.setAction(Intent.ACTION_SEND);
//                                    sendIntent.putExtra(Intent.EXTRA_TEXT, "");
//                                    sendIntent.setType("text/plain");
//                                    sendIntent.setPackage("com.facebook.orca");
                try {
                    mContext.startActivity(sendIntent);
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(),"Please Install Facebook Messenger", Toast.LENGTH_LONG).show();
                }
//                                    intent.setType("text/plain");
//                                    intent.putExtra(Intent.EXTRA_SUBJECT,  LoggedUserName+" via On-Sinai");
//                                    intent.putExtra(Intent.EXTRA_TEXT, "");
//                                    intent.setData(Uri.parse("mailto:"+email.get(position)+LoggedFB_ID)); // or just "mailto:" for blank
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
//                                    startActivity(intent);
            }
        });
//                if (dialougeView.getParent()!=null){
//                    ((ViewGroup)dialougeView.getParent()).removeView(dialougeView);
//                }
        alertDialog = builder.create();
        alertDialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
    }

    int total=0;
    boolean hadPosition=false;
    int Posnum;
    View itemView;
    int position_launch;
    @TargetApi(21)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final OptionsEntity feedItem=feedItemList.get(position);
        itemView = convertView;
        if (itemView == null){
            inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(android.R.layout.post_list_item, parent, false);
            itemView.setTag(postHolder);
        }else{
            postHolder = (PostHolder) itemView.getTag();
        }
        postHolder = new PostHolder();
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (position>=1){
                    Posnum=position;
                    return hadPosition =true;
                }else return hadPosition=false;
            }
        });
        postHolder.profile_picture = (CircleImageView) itemView.findViewById(android.R.id.post_profile_picture);
        postHolder.profile_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position_launch=position;
                LaunchQuickReachView();
            }
        });
        postHolder.username = (TextView) itemView.findViewById(android.R.id.post_username);
        postHolder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position_launch=position;
                LaunchQuickReachView();
            }
        });
        postHolder.Distance=(TextView)itemView.findViewById(android.R.id.post_user_location);
        postHolder.Date=(TextView)itemView.findViewById(android.R.id.post_Date);
        postHolder.txt_required_all=(TextView)itemView.findViewById(android.R.id.txt_required_all);
        postHolder.post_content = (TextView)itemView.findViewById(android.R.id.post_text);
        postHolder.post_image = (ImageView)itemView.findViewById(android.R.id.post_image);
        postHolder.Image_arraw_down=(ImageView) itemView.findViewById(android.R.id.Image_arraw_down);
        postHolder.volunteer = (TextView)itemView.findViewById(android.R.id.post_volunteer);
        postHolder.Linear_AllPostDetails=(LinearLayout)itemView.findViewById(android.R.id.Linear_AllPostDetails);
        postHolder.PostsListItemHeader=(LinearLayout)itemView.findViewById(android.R.id.PostsListItemHeader);
        postHolder.contibuters_launcher=(LinearLayout)itemView.findViewById(android.R.id.contibuters_launcher);
        postHolder.Post_ContributionDetails_Linear=(LinearLayout)itemView.findViewById(android.R.id.Post_ContributionDetails_Linear);
        postHolder.Post_ContributionDetails_Linear_3=(LinearLayout)itemView.findViewById(android.R.id.Post_ContributionDetails_Linear_3);
        postHolder.txt_required_title=(TextView)itemView.findViewById(android.R.id.txt_required_title);
        postHolder.txt_required_all=(TextView)itemView.findViewById(android.R.id.txt_required_all);
        postHolder.txt_required_paid=(TextView)itemView.findViewById(android.R.id.txt_required_paid);
        postHolder.img_dolarsign=(ImageView)itemView.findViewById(android.R.id.img_dolarsign);
        postHolder.Linear_ImageArrowDown_Post=(LinearLayout)itemView.findViewById(android.R.id.Linear_ImageArrowDown_Post);
        postHolder.Linear_ImageArrowDown_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(mContext,postHolder.Linear_ImageArrowDown_Post);
                popupMenu.getMenuInflater().inflate(android.R.menu.post_menu_control,popupMenu.getMenu());
                popupMenu.setGravity(Gravity.CENTER_VERTICAL);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().equals(mContext.getString(android.R.string.edit_post))){
                            editPostDialogue();
                        }else if (item.getTitle().toString().equals(mContext.getString(android.R.string.Remove_post))){
                            removePostDialogue();
                        }else if (item.getTitle().toString().equals(mContext.getString(android.R.string.report_post))){
                            ReportPostDialogue();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        postHolder.view_header_post=(View)itemView.findViewById(android.R.id.view_header);
        postHolder.view_center_post=(View)itemView.findViewById(android.R.id.view_center_post);
        postHolder.Linear_ContributionDetails_Post=(LinearLayout)itemView.findViewById(android.R.id.Linear_ContributionDetails_Post);
        postHolder.Linear_ContributionDetails_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get position
                position_launch=position;
                getpostdatatogetPostID(position_launch);
//                getPosTDate = ((TextView) (itemView.findViewById(R.id.post_Date))).getText().toString();
//                getPosTAddress = ((TextView) (itemView.findViewById(R.id.post_user_location))).getText().toString();
//                getUserName = ((TextView) (itemView.findViewById(R.id.post_username))).getText().toString();
//                getPosTRequired = ((TextView) (itemView.findViewById(R.id.txt_required_all))).getText().toString();

                if (!getComments.matches("null")||!getComments.matches("")||!getComments.matches("0")) {
                    OptionsEntity optionsEntity= dbHelper.getSelectedPostID(getUserName, getPosTDate, getPosTAddress, getPosTRequired);
                    Intent intent = new Intent(mContext, Comments.class);
                    Bundle b = new Bundle();
                    b.putSerializable("PostID", optionsEntity.getPostID());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtras(b);
                    mContext.startActivity(intent);
                }else if (!getShares.matches("0") ||!getShares.matches("null")|| !getShares.matches("")){
                    Intent intent = new Intent(mContext,CollaboratorsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }else {
                    Toast.makeText(mContext, "No comments nor shares", Toast.LENGTH_LONG).show();
                }
            }
        });
        postHolder.Linear_Dislike_post=(LinearLayout)itemView.findViewById(android.R.id.Linear_Dislike_post);
        postHolder.post_dislike_num=(TextView)itemView.findViewById(android.R.id.post_dislike_num);
        postHolder.post_dislike_word=(TextView)itemView.findViewById(android.R.id.post_dislike_word);
        postHolder.view_vertical_post_dislike_left=(View)itemView.findViewById(android.R.id.view_vertical_post_dislike_left);
        postHolder.Linear_Volunteer_post=(LinearLayout)itemView.findViewById(android.R.id.Linear_Volunteer_post);
//        postHolder.post_volunteer_num=(TextView)itemView.findViewById(R.id.post_volunteer_num);
        postHolder.post_Like=(TextView)itemView.findViewById(android.R.id.post_Like);
        postHolder.txt_comments_num=(TextView)itemView.findViewById(android.R.id.txt_comments_num);
        postHolder.txt_shares_num=(TextView)itemView.findViewById(android.R.id.txt_shares_num);
        postHolder.post_volunteer_word=(TextView)itemView.findViewById(android.R.id.post_volunteer_word);
        postHolder.Text_Reactions_Num=(TextView)itemView.findViewById(android.R.id.Text_Reactions_Num);
        postHolder.view_vertical_post_volunteer_right=(View)itemView.findViewById(android.R.id.view_vertical_post_volunteer_right);
        postHolder.Linear_post_Donations_above=(LinearLayout)itemView.findViewById(android.R.id.Linear_post_Donations_above);
        postHolder.post_donate_num=(TextView)itemView.findViewById(android.R.id.txt_post_donate);
        postHolder.post_donate_word=(TextView)itemView.findViewById(android.R.id.post_donate_word);
        postHolder.view_vertical_post_donation=(View)itemView.findViewById(android.R.id.view_vertical_post_donation);
        postHolder.Linear_Post_Reaction_images=(LinearLayout)itemView.findViewById(android.R.id.Linear_Post_Reaction_images);
        postHolder.LinearLikeImage=(LinearLayout)itemView.findViewById(android.R.id.LinearLikeImage);
        postHolder.LinearLikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Config.LoggedEmail!=null&&Config.LoggedUserName!=null){
                    getPosTText= ((TextView)(itemView.findViewById(android.R.id.post_text))).getText().toString();
                    getPosTDate= ((TextView)(itemView.findViewById(android.R.id.post_Date))).getText().toString();
                    postFragment.startFetchingActionsJson("K",getPosTText,getPosTDate, Config.LoggedEmail, Config.LoggedUserName);
                }else {
                    DialogueUnavailableDueRegisteration();
                }
            }
        });
//        postHolder.LinearShareImage=(LinearLayout)itemView.findViewById(R.id.LinearShareImage);
//        postHolder.LinearShareImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Config.LoggedEmail!=null&&Config.LoggedUserName!=null){
//                    getPosTText= ((TextView)(itemView.findViewById(R.id.post_text))).getText().toString();
//                    getPosTDate= ((TextView)(itemView.findViewById(R.id.post_Date))).getText().toString();
//                    postFragment.startFetchingActionsJson("SH",getPosTText,getPosTDate, Config.LoggedEmail, Config.LoggedUserName);
//                }else {
//                    DialogueUnavailableDueRegisteration();
//                }
//            }
//        });
//        postHolder.hand_logo=(ImageView)itemView.findViewById(R.id.hand_logo) ;
//        postHolder.hand_logo.setFocusable(false);
//        postHolder.post_dislike=(TextView)itemView.findViewById(R.id.post_dislike);
//        postHolder.post_dislike.setFocusable(false);
        postHolder.view_footer_post=(View)itemView.findViewById(android.R.id.view_footer_post);
        postHolder.heart_logo=(ImageView)itemView.findViewById(android.R.id.heart_logo);
        postHolder.post_volunteer=(TextView)itemView.findViewById(android.R.id.post_volunteer);
        postHolder.volunteer_Image_Linear=(LinearLayout)itemView.findViewById(android.R.id.volunteer_Image_Linear);
        postHolder.volunteer_Image_Linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check whether post volunteered or not if 0 change image to red heart and set value to 1
                // else, if user volunteered it, set value to 0 and change image
//                if (feedItem.getVolunteerNeighborsStatus()=="0"){
//                    Picasso.with(mContext).load(R.drawable.volunteer_edit).into(postHolder.heart_logo);
                    //insert 1 in specific column
//                }else if (feedItem.getVolunteerNeighborsStatus()=="1"){
//                    Picasso.with(mContext).load(R.drawable.blackheartempty).into(postHolder.heart_logo);
//                }
                if (Config.LoggedEmail!=null&&Config.LoggedUserName!=null){
                    getPosTText= ((TextView)(itemView.findViewById(android.R.id.post_text))).getText().toString();
                    getPosTDate= ((TextView)(itemView.findViewById(android.R.id.post_Date))).getText().toString();
                    postFragment.startFetchingActionsJson("V",getPosTText,getPosTDate, Config.LoggedEmail, Config.LoggedUserName);
                }else {
                    DialogueUnavailableDueRegisteration();
                }
            }
        });
        postHolder.view_vertical_footer_left=(View)itemView.findViewById(android.R.id.view_vertical_footer_left);
        postHolder.LinearShareImage=(LinearLayout)itemView.findViewById(android.R.id.Linear_share);
        postHolder.LinearShareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        postHolder.Linear_donations_post=(LinearLayout)itemView.findViewById(android.R.id.Linear_donations_post);
        postHolder.Linear_donations_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Config.LoggedEmail!=null&&Config.LoggedUserName!=null){
                    getPosTText= ((TextView)(itemView.findViewById(android.R.id.post_text))).getText().toString();
                    getPosTDate= ((TextView)(itemView.findViewById(android.R.id.post_Date))).getText().toString();
                    postFragment.startFetchingActionsJson("D",getPosTText,getPosTDate, Config.LoggedEmail, Config.LoggedUserName);
                }else {
                    DialogueUnavailableDueRegisteration();
                }
            }
        });
        postHolder.donatebutton_logo=(ImageView)itemView.findViewById(android.R.id.donatebutton_logo);
        postHolder.txt_post_donate=(TextView)itemView.findViewById(android.R.id.txt_post_donate);
        Picasso.with(mContext).load(feedItem.getProfile_Images()).error(android.R.drawable.male).into(postHolder.profile_picture);
        Picasso.with(mContext).load(android.R.drawable.blackheartempty).into(postHolder.heart_logo);
        postHolder.username.setText(feedItem.getUserName());
        postHolder.Distance.setText(feedItem.getAddress());
        postHolder.Date.setText(feedItem.getDate());
        postHolder.txt_required_all.setText(feedItem.getRequired());
        postHolder.txt_required_paid.setText(feedItem.getPaid());
        postHolder.post_content.setText(feedItem.getPost_Text());
        postHolder.contibuters_launcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position_launch=position;
                getpostdatatogetPostID(position_launch);
                if (!getComments.matches("null")||!getComments.matches("")||!getComments.matches("0")) {
                    OptionsEntity optionsEntity= dbHelper.getSelectedPostID(getUserName, getPosTDate, getPosTAddress, getPosTRequired);
                    Intent intent = new Intent(mContext, CollaboratorsActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("PostID", optionsEntity.getPostID());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtras(b);
                    mContext.startActivity(intent);
                }
            }
        });


        if (feedItem.Shares==null) {
            postHolder.txt_shares_num.setText("");
        }else if (feedItem.Shares.matches("null")) {
            postHolder.txt_shares_num.setText("");
        }else if (feedItem.Shares=="0"){
            postHolder.txt_shares_num.setText("");
        }else {
            postHolder.txt_shares_num.setText(feedItem.getShares()+" shares");
        }

        if (feedItem.commentsNums==null) {
            postHolder.txt_comments_num.setText("");
        }else if (feedItem.commentsNums.matches("null")) {
            postHolder.txt_comments_num.setText("");
        }else if (feedItem.commentsNums=="0"){
            postHolder.txt_comments_num.setText("");
        }else {
            postHolder.txt_comments_num.setText(feedItem.getCommentsNums()+" comments");
        }
        Picasso.with(mContext)
                .load(feedItem.getPost_images() )
                .memoryPolicy(MemoryPolicy.NO_CACHE )
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .into(postHolder.post_image);
//        if(feedItem.getVolunteer()==null){
//            int volun=0;
//            postHolder.post_volunteer_num.setText(volun);
//        }else {
//            postHolder.post_volunteer_num.setText(feedItem.getVolunteer());
//        }
        String Volun = "0";
        String Don="0";
        String Likes="0";
        String shares="0";

        total=0;
        if (feedItem.Volunteer==null) {
//            postHolder.post_volunteer_num.setText(String.valueOf(Volun));
        }else if (feedItem.Volunteer.matches("null")) {
//            postHolder.post_volunteer_num.setText(String.valueOf(Volun));
        }else if (feedItem.Volunteer=="0"){
//            postHolder.post_volunteer_num.setText(String.valueOf(Volun));
        }else {
//            postHolder.post_volunteer_num.setText(feedItem.getVolunteer());
            total+=Integer.parseInt(feedItem.getVolunteer());
        }

        if (feedItem.Donate==null) {
//            postHolder.post_donate_num.setText(String.valueOf(Don));
        }else if (feedItem.Donate.matches("null")) {
//            postHolder.post_donate_num.setText(String.valueOf(Don));
        }else if (feedItem.Donate=="0"){
//            postHolder.post_donate_num.setText(String.valueOf(Don));
        }else {
//            postHolder.post_donate_num.setText(feedItem.getDonate() );
            total+=Integer.parseInt(feedItem.getDonate());
        }

        if (feedItem.Likes==null) {
//            postHolder.post_Like.setText(String.valueOf(Likes));
        }else if (feedItem.Donate=="0"){
//            postHolder.post_Like.setText(String.valueOf(Likes));
        }else if(feedItem.Likes.matches("null")) {
//            postHolder.post_Like.setText(String.valueOf(Likes));
        }else {
//            postHolder.post_Like.setText(feedItem.getLikes());
            total+=Integer.parseInt( feedItem.getLikes());
        }
        postHolder.Text_Reactions_Num.setText("You, Noha Mohammed and "+String.valueOf(total)+" others");
        return itemView;
    }

    private void getpostdatatogetPostID(int position) {
        getComments=feedItemList.get(position).getCommentsNums();
        getShares=feedItemList.get(position).getShares();
        getUserName=feedItemList.get(position).getUserName();
        getPosTDate=feedItemList.get(position).getDate();
        getPosTAddress=feedItemList.get(position).getAddress();
        getPosTRequired=feedItemList.get(position).getRequired();
    }

    PostFragment postFragment=new PostFragment();

    private void ReportPostDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialougeView = inflater.inflate(android.R.layout.report_post_layout, null);
        final TextView txt_del_post= (TextView) dialougeView.findViewById(android.R.id.txt_del_post);
        getPosTText=((TextView)(itemView.findViewById(android.R.id.post_text))).getText().toString();
        getPosTDate=((TextView)(itemView.findViewById(android.R.id.post_Date))).getText().toString();
        getUserName=((TextView)(itemView.findViewById(android.R.id.post_username))).getText().toString();

        builder.setView(dialougeView)
                .setPositiveButton(String.valueOf(android.R.string.Delete), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //    Implement Deleteing
                    }
                });
        builder.setNegativeButton(String.valueOf(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog Dialogue = builder.create();
        Dialogue.show();
    }

    private void removePostDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialougeView = inflater.inflate(android.R.layout.delete_post_layout, null);
        final TextView txt_del_post= (TextView) dialougeView.findViewById(android.R.id.txt_del_post);
        getPosTText=((TextView)(itemView.findViewById(android.R.id.post_text))).getText().toString();
        getPosTDate=((TextView)(itemView.findViewById(android.R.id.post_Date))).getText().toString();
        getUserName=((TextView)(itemView.findViewById(android.R.id.post_username))).getText().toString();
        builder.setView(dialougeView)
                .setPositiveButton(String.valueOf(android.R.string.Delete), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //    Implement Deleteing
                    }
                });
        builder.setNegativeButton(String.valueOf(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog Dialogue = builder.create();
        Dialogue.show();
    }

    String getPosTText,getPosTDate,getUserName, getPosTAddress, getPosTRequired, getComments, getShares;

    private void editPostDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialougeView  = inflater.inflate(android.R.layout.update_post_layout, null);
        final EditText Edit_Text_UpdatePost = (EditText) dialougeView.findViewById(android.R.id.Edit_Text_UpdatePost);
        Button Btn_Update_Post_UploadPicture = (Button) dialougeView.findViewById(android.R.id.Btn_Update_Post_UploadPicture);
        getPosTText=((TextView)(itemView.findViewById(android.R.id.post_text))).getText().toString();
        getPosTDate=((TextView)(itemView.findViewById(android.R.id.post_Date))).getText().toString();
        getUserName=((TextView)(itemView.findViewById(android.R.id.post_username))).getText().toString();
        Edit_Text_UpdatePost.setText(getPosTText);
        builder.setView(dialougeView).setPositiveButton(String.valueOf(android.R.string.btn_update), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String Edit_Text_UpdatePost_STR = Edit_Text_UpdatePost.getText().toString();
                    }
                });
        builder.setNegativeButton(String.valueOf(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog Dialogue = builder.create();
        Dialogue.show();
    }

    private class PostHolder{
        View view_header_post,view_center_post,view_vertical_post_dislike_left,view_vertical_post_volunteer_right,view_vertical_post_donation,
                view_footer_post,view_vertical_footer_left;
        CircleImageView profile_picture;
        TextView username;
        TextView user_description;
        TextView Distance;
        TextView Date;
        TextView post_content;
        ImageView post_image;//contributers_image;
        ImageView Image_arraw_down,img_dolarsign,hand_logo,heart_logo,donatebutton_logo;
        TextView volunteer;
        TextView donate;
        TextView both,txt_required_title,txt_required_all,txt_required_paid,post_dislike_num,post_dislike_word,post_donate_num,post_donate_word,post_dislike,post_volunteer,txt_post_donate,
                post_volunteer_num,post_volunteer_word, post_Like, Text_Reactions_Num, txt_comments_num, txt_shares_num;
        LinearLayout Linear_AllPostDetails,contibuters_launcher,PostsListItemHeader,Post_ContributionDetails_Linear,Post_ContributionDetails_Linear_2
                ,Post_ContributionDetails_Linear_3,Linear_ImageArrowDown_Post,Linear_ContributionDetails_Post,Linear_Dislike_post,
                Linear_Volunteer_post,Linear_post_Donations_above,Linear_Post_Reaction_images,volunteer_Image_Linear,
                Linear_donations_post, LinearLikeImage, LinearShareImage;
    }

    public void DialogueUnavailableDueRegisteration(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//        please sign in or up to Favorite Your Place.\n\nyourbest-online.com\nThanks
        builder.setMessage(mContext.getString(android.R.string.unavailabledueregisteration))
                .setTitle(mContext.getString(android.R.string.contributiondenied))
                .setPositiveButton(mContext.getString(android.R.string.TryFree), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent_Registeration = new Intent(mContext, MainActivity.class);
                        intent_Registeration.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent_Registeration);
                    }
                });
        builder.setNegativeButton(mContext.getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        // Create the AlertDialog object and return it
        AlertDialog Dialogue = builder.create();
        Dialogue.show();
//        smoothCheckBox.setChecked(false);
    }

}