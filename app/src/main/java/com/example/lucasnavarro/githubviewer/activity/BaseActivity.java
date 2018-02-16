package com.example.lucasnavarro.githubviewer.activity;

        import android.support.v7.app.AppCompatActivity;

        import com.example.lucasnavarro.githubviewer.event.RequestUserEvent;

        import org.greenrobot.eventbus.EventBus;
        import org.greenrobot.eventbus.Subscribe;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe
    public void onEvent(RequestUserEvent event){}

}
