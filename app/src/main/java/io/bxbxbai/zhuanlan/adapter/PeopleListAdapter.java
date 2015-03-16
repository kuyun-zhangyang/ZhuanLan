package io.bxbxbai.zhuanlan.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import io.bxbxbai.zhuanlan.App;
import io.bxbxbai.zhuanlan.R;
import io.bxbxbai.zhuanlan.bean.User;
import io.bxbxbai.zhuanlan.utils.Utils;
import io.bxbxbai.zhuanlan.utils.ZhuanLanApi;
import io.bxbxbai.zhuanlan.view.CircleImageView;

import java.util.List;

/**
 *
 * @author bxbxbai
 */
public class PeopleListAdapter extends SimpleBaseAdapter<User> {


    public PeopleListAdapter(Context context, List<User> data) {
        super(context, data);
    }

    @Override
    public int getItemResource() {
        return R.layout.layout_people_info;
    }

    @Override
    public View getItemView(int position, View convertView, ViewHolder holder) {
        User user = getItem(position);

        final CircleImageView imageView = holder.findView(R.id.avatar);

        String id = user.getAuthor().getAvatar().getId();
        String picUrl = Utils.getAuthorAvatarUrl(user.getAuthor().getAvatar().getTemplate(),
                id, ZhuanLanApi.PIC_SIZE_XL);

//        imageView.setImageUrl(picUrl, App.getInstance().getImageLoader());
        Picasso.with(context).load(picUrl).placeholder(R.drawable.bxbxbai).into(imageView);

        TextView name = holder.findView(R.id.tv_name);
        name.setText(user.getName());

        TextView follower = holder.findView(R.id.tv_follower);
        follower.setText(context.getString(R.string.follower, user.getFollowerCount()));

        TextView postCount = holder.findView(R.id.tv_post_count);
        postCount.setText(context.getString(R.string.post_count, user.getPostCount()));

        TextView description = holder.findView(R.id.tv_description);
        description.setText(user.getAuthor().getBio());

        convertView.setTag(R.id.key_data, user.getSlug());
        return convertView;
    }
}