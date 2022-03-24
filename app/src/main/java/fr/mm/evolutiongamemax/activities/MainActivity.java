package fr.mm.evolutiongamemax.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import fr.mm.evolutiongamemax.R;
import fr.mm.evolutiongamemax.bibliotheques.MaximeToolsLayoutV1;
import fr.mm.evolutiongamemax.staticClasses.MaximeStatic;

public class MainActivity extends AppCompatActivity {

    int timer = 0;
    ConstraintLayout background;
    ImageView logoImgView;
    TextView welcomeTextView, loadingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = findViewById(R.id.main_background_constraintLayout);
        logoImgView = findViewById(R.id.main_logo_imageView);
        welcomeTextView = findViewById(R.id.main_welcome_textView);
        loadingTextView = findViewById(R.id.main_loading_textView);

        // animation Ouverture activity :
        Animation animAlphaAppear = AnimationUtils.loadAnimation(this, R.anim.animation_in_alpha_appear_textview);
        animAlphaAppear.reset();
        background.clearAnimation();
        background.startAnimation(animAlphaAppear);
        Animation animFromTopAppear = AnimationUtils.loadAnimation(this, R.anim.animation_in_fromtop_textview);
        animAlphaAppear.reset();
        logoImgView.clearAnimation();
        logoImgView.startAnimation(animFromTopAppear);
        welcomeTextView.clearAnimation();
        welcomeTextView.startAnimation(animFromTopAppear);
        Animation animFromLeftAppear = AnimationUtils.loadAnimation(this, R.anim.animation_in_fromleft_textview);
        animAlphaAppear.reset();
        loadingTextView.clearAnimation();
        loadingTextView.startAnimation(animFromLeftAppear);


        // animation dynamic Background :
        background.setBackgroundResource(R.drawable.animation_gradient_background_main_activity);
        AnimationDrawable animationGradientBackground = (AnimationDrawable) background.getBackground();
        animationGradientBackground.setEnterFadeDuration(50);
        animationGradientBackground.setExitFadeDuration(3000);
        animationGradientBackground.start();

        // MUSIC And SOUND INITIALIZER :
        if (MaximeStatic.mainMusic == null) {
            MaximeStatic.mainMusic = MaximeToolsLayoutV1.setMusicMediaPlayer(this, R.raw.silent_poets_asylums_va1);
            MaximeToolsLayoutV1.musicMediaPlayerPlay(MaximeStatic.mainMusic, true);
        }
        if (MaximeStatic.soundClickPositive == null) {
            MaximeStatic.soundClickPositive = MaximeToolsLayoutV1.setMusicMediaPlayer(this, R.raw.ed_goutte_positif_original);
        }

        Thread th1 = new Thread(() -> {
            while (timer < 50) {
                timer++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.e("MAXIME_ERROR", "main activity th1 run ERROR");
                }
            }
            Intent intent = new Intent(this, GameViewActivity.class);
            startActivity(intent);

        });

        th1.start();

    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onClickMethod(View view) {
        timer = 50;
    }
}