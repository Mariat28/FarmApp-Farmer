package ug.global.glofarmedited;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import ug.global.glofarmedited.agromarket.FarmTools;
import ug.global.glofarmedited.agromarket.ProductsActivityMain;
import ug.global.glofarmedited.agromarket.SuppliesActivityMain;
import ug.global.glofarmedited.farmfinancials.FarmFinancialsMain;
import ug.global.glofarmedited.supporttools.SupportToolsMain;

public class Home extends AppCompatActivity {
    TextView farmname, total_stock, sales;
    ImageView sendbutton;
    String retrievedfarmname;
    TextInputEditText openingstock;
    String intentuserid;
    boolean card_is_shown = false;
    TextView supplies, farm_tools, products;
    MaterialCardView agromarketcard, farmfinancialscard, supporttoolscard, inputtrackingcard, market_card_panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        String intentfarmname = intent.getStringExtra("farmname");
        intentuserid = intent.getStringExtra("userid");
        setTitle(intentfarmname);
        farmfinancialscard = findViewById(R.id.farmfinancialscard);
        agromarketcard = findViewById(R.id.agromarket);
        supporttoolscard = findViewById(R.id.supporttoolscard);
        market_card_panel = findViewById(R.id.market_card_panel);
        inputtrackingcard = findViewById(R.id.inputtrackingcard);
        supplies = findViewById(R.id.supplies);
        farm_tools = findViewById(R.id.farm_tools);
        products = findViewById(R.id.products);
        final TextView[] textViews = {supplies, products, farm_tools};
        for (final TextView textView : textViews) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    card_is_shown = false;
                    openMarket(textView.getId());
                    market_card_panel.setVisibility(View.INVISIBLE);
                    market_card_panel.animate().translationY(4).alpha(0.9f).setListener(null);
                }
            });
        }

        agromarketcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!card_is_shown) {
                    card_is_shown = true;
                    market_card_panel.setVisibility(View.VISIBLE);
                    market_card_panel.setAlpha(0.0f);
                    market_card_panel.animate().translationY(-4).alpha(0.9f).setListener(null);

                }
            }
        });
        farmfinancialscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, FarmFinancialsMain.class);
                intent.putExtra("userid", intentuserid);
                startActivity(intent);

            }
        });
        supporttoolscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, SupportToolsMain.class);
                startActivity(intent);


            }
        });
        inputtrackingcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "Feature Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void openMarket(int text_view_id) {
        switch (text_view_id) {
            case R.id.supplies:
                startActivity(new Intent(this, SuppliesActivityMain.class));
                break;
            case R.id.farm_tools:
                startActivity(new Intent(this, FarmTools.class));
                break;
            case R.id.products:
                Intent i = new Intent(this, ProductsActivityMain.class);
                startActivity(i);

                break;
        }
    }
}

