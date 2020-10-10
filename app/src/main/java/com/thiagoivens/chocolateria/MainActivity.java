package com.thiagoivens.chocolateria;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.thiagoivens.chocolateria.models.order.OrderFragment;
import com.thiagoivens.chocolateria.models.product.ProductFragment;

public class MainActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Adicionar os fragmentos aqui
        adapter.AddFragment(new ProductFragment(), "");
        adapter.AddFragment(new OrderFragment(), "");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        //Adiciona os icones ao tabView
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_store);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_shopping_cart);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }

}

