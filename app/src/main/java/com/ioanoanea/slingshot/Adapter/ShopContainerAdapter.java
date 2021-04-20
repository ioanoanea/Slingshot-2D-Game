package com.ioanoanea.slingshot.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.ioanoanea.slingshot.Activity.BuyObjectActivity;
import com.ioanoanea.slingshot.Animation.ViewAnimator;
import com.ioanoanea.slingshot.BuildConfig;
import com.ioanoanea.slingshot.Manager.BulletManager;
import com.ioanoanea.slingshot.Manager.CoinManager;
import com.ioanoanea.slingshot.R;
import com.ioanoanea.slingshot.ViewHolder.BuyBulletsViewHolder;
import com.ioanoanea.slingshot.ViewHolder.BuyCoinsViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopContainerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final PurchasesUpdatedListener purchasesUpdatedListener;
    private final BillingClient billingClient;
    private CoinManager coinManager;
    private BulletManager bulletManager;
    private ViewAnimator viewAnimator;
    private HashMap<String, Integer> purchasesDetails;
    private OnCoinsPurchasedListener onCoinsPurchasedListener;

    public ShopContainerAdapter(Context context){
        this.context = context;
        this.coinManager = new CoinManager(context);
        this.bulletManager = new BulletManager(context);
        this.viewAnimator = new ViewAnimator(context);
        this.purchasesDetails = new HashMap<>();

        // set purchases amount details
        purchasesDetails.put("1", 10);
        purchasesDetails.put("2", 25);
        purchasesDetails.put("3", 50);
        purchasesDetails.put("4", 100);
        purchasesDetails.put("5", 250);
        purchasesDetails.put("6", 500);

        // set purchases update listener
        purchasesUpdatedListener = new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(@NonNull BillingResult billingResult, List<Purchase> purchases) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                        && purchases != null) {
                    for (Purchase purchase : purchases) {
                        handlePurchase(purchase);
                    }
                } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
                    // Handle an error caused by a user cancelling the purchase flow.
                    Log.d("ShopActivity", "User canceled the purchase flow");
                } else {
                    // Handle any other error codes.
                    Log.d("ShopActivity", "error");
                }

            }
        };

        // set billing client
        billingClient = BillingClient.newBuilder(context)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View bulletsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_bullets_card, parent,false);
        View coinsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_coins_card, parent,false);

        // initialize viewHolder
        if (viewType == 1) {
            return new BuyCoinsViewHolder(coinsView);
        }
        return new BuyBulletsViewHolder(bulletsView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            final BuyBulletsViewHolder viewHolder = (BuyBulletsViewHolder) holder;

            // animate item background
            viewAnimator.animate(viewHolder.item5, ViewAnimator.BOUNCE);
            viewAnimator.animate(viewHolder.item25, ViewAnimator.BOUNCE, ViewAnimator.DURATION);
            viewAnimator.animate(viewHolder.item50, ViewAnimator.BOUNCE, 2 * ViewAnimator.DURATION);

            // animate item images
            viewAnimator.animate(viewHolder.itemView, ViewAnimator.BOUNCE);

            // on item 5 bullets click
            viewHolder.item5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(context, "You bought 5 bullets", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, BuyObjectActivity.class);
                    intent.putExtra("COUNT", 5);
                    intent.putExtra("PRICE", 50);
                    context.startActivity(intent);
                }
            });

            // on item 25 bullets click
            viewHolder.item25.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(context, "You bought 25 bullets", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, BuyObjectActivity.class);
                    intent.putExtra("COUNT", 25);
                    intent.putExtra("PRICE", 250);
                    context.startActivity(intent);
                }
            });

            // on item 50 bullets click
            viewHolder.item50.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(context, "You bought 50 bullets", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, BuyObjectActivity.class);
                    intent.putExtra("COUNT", 50);
                    intent.putExtra("PRICE", 500);
                    context.startActivity(intent);
                }
            });
        }

        if (holder.getItemViewType() == 1) {
            // set view holder
            if (BuildConfig.DEBUG && !(holder instanceof BuyCoinsViewHolder)) {
                throw new AssertionError("Assertion failed");
            }
            final BuyCoinsViewHolder viewHolder = (BuyCoinsViewHolder) holder;


            // animate views
            viewAnimator.animate(viewHolder.item10, ViewAnimator.BOUNCE);
            viewAnimator.animate(viewHolder.item25, ViewAnimator.BOUNCE, ViewAnimator.DURATION);
            viewAnimator.animate(viewHolder.item50, ViewAnimator.BOUNCE, 2 * ViewAnimator.DURATION);
            viewAnimator.animate(viewHolder.item100, ViewAnimator.BOUNCE);
            viewAnimator.animate(viewHolder.item250, ViewAnimator.BOUNCE, ViewAnimator.DURATION);
            viewAnimator.animate(viewHolder.item500, ViewAnimator.BOUNCE, 2 * ViewAnimator.DURATION);

            // Establish a connection to Google Play
            billingClient.startConnection(new BillingClientStateListener() {
                @Override
                public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                    if (billingResult.getResponseCode() ==  BillingClient.BillingResponseCode.OK) {
                        // The BillingClient is ready. You can query purchases here.
                        if (billingClient.isReady()){
                            // set items list
                            ArrayList<String> skuList = new ArrayList<>();
                            skuList.add("1");
                            skuList.add("2");
                            skuList.add("3");
                            skuList.add("4");
                            skuList.add("5");
                            skuList.add("6");
                            SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                            params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
                            billingClient.querySkuDetailsAsync(params.build(),
                                    new SkuDetailsResponseListener() {
                                        @Override
                                        public void onSkuDetailsResponse(@NonNull BillingResult billingResult,
                                                                         List<SkuDetails> skuDetailsList) {
                                            // Process the result.
                                            // set items prices
                                            viewHolder.priceItem10.setText(String.valueOf(skuDetailsList.get(0).getPrice()));
                                            viewHolder.priceItem25.setText(String.valueOf(skuDetailsList.get(1).getPrice()));
                                            viewHolder.priceItem50.setText(String.valueOf(skuDetailsList.get(2).getPrice()));
                                            viewHolder.priceItem100.setText(String.valueOf(skuDetailsList.get(3).getPrice()));
                                            viewHolder.priceItem250.setText(String.valueOf(skuDetailsList.get(4).getPrice()));
                                            viewHolder.priceItem500.setText(String.valueOf(skuDetailsList.get(5).getPrice()));
                                        }
                                    });

                        }
                    }
                }
                @Override
                public void onBillingServiceDisconnected() {
                    // Try to restart the connection on the next request to
                    // Google Play by calling the startConnection() method.
                    billingClient.startConnection(this);
                }
            });

            // on item 10 coins click
            viewHolder.item10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, "You got 10 coins", Toast.LENGTH_SHORT).show();
                    purchaseItem(0);
                }
            });

            // on item 25 coins click
            viewHolder.item25.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(context, "You got 25 coins", Toast.LENGTH_SHORT).show();
                    purchaseItem(1);
                }
            });

            // on item 50 coins click
            viewHolder.item50.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(context, "You got 50 coins", Toast.LENGTH_SHORT).show();
                    purchaseItem(2);
                }
            });

            // on item 100 coins click
            viewHolder.item100.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(context, "You got 100 coins", Toast.LENGTH_SHORT).show();
                    purchaseItem(3);
                }
            });

            // on item 250 coins click
            viewHolder.item250.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(context, "You got 250 coins", Toast.LENGTH_SHORT).show();
                    purchaseItem(4);
                }
            });

            // on item 500 coins click
            viewHolder.item500.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(context, "You get 500 coins", Toast.LENGTH_SHORT).show();
                    purchaseItem(5);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    /**
     * Purchase an item
     * @param item (int) item index
     */
    private void purchaseItem(int item){
        if(billingClient.isReady()){
            ArrayList<String> skuList = new ArrayList<> ();
            skuList.add("1");
            skuList.add("2");
            skuList.add("3");
            skuList.add("4");
            skuList.add("5");
            skuList.add("6");
            SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
            params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
            billingClient.querySkuDetailsAsync(params.build(), new SkuDetailsResponseListener() {
                @Override
                public void onSkuDetailsResponse(@NonNull BillingResult billingResult, List<SkuDetails> skuDetailsList) {
                    if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
                        //Toast.makeText(BuyCoinsActivity.this, "Loading product...", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(BuyCoinsActivity.this, String.valueOf(skuDetailsList.get(0).getTitle() + " " + skuDetailsList.get(0).getPrice()), Toast.LENGTH_SHORT).show();
                        try{
                            // Retrieve a value for "skuDetails" by calling querySkuDetailsAsync().
                            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                    .setSkuDetails(skuDetailsList.get(item))
                                    .build();
                            int responseCode = billingClient.launchBillingFlow((Activity) context, billingFlowParams).getResponseCode();
                        } catch (Exception e) {
                            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    } else Toast.makeText(context, "" + billingResult.getResponseCode(), Toast.LENGTH_SHORT).show();
                }
            });

        } else Toast.makeText(context, "Billing client is not responding!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Handle the purchase
     * @param purchase (Purchase) purchase object
     */
    private void handlePurchase(Purchase purchase){
        // Purchase retrieved from BillingClient#queryPurchases or your PurchasesUpdatedListener.

        // Verify the purchase.
        // Ensure entitlement was not already granted for this purchaseToken.
        // Grant entitlement to the user.

        ConsumeParams consumeParams =
                ConsumeParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();

        ConsumeResponseListener listener = new ConsumeResponseListener() {
            @Override
            public void onConsumeResponse(BillingResult billingResult, @NonNull String purchaseToken) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // Handle the success of the consume operation.
                    CoinManager coinManager = new CoinManager(context);
                    // coinManager.addCoins(purchasesDetails.get(purchase.getOrderId()));
                    // notify purchase
                    onCoinsPurchasedListener.onPurchased();
                    Toast.makeText(context, purchase.getOrderId(), Toast.LENGTH_SHORT).show();
                }
            }
        };

        billingClient.consumeAsync(consumeParams, listener);

    }

    /**
     * Set on coins purchased listener
     * @param onCoinsPurchasedListener - listener
     */
    public void setOnCoinsPurchasedListener(OnCoinsPurchasedListener onCoinsPurchasedListener) {
        this.onCoinsPurchasedListener = onCoinsPurchasedListener;
    }

    /**
     * Handles coins purchases events
     */
    private interface OnCoinsPurchasedListener {
        /**
         * Method called when a purchase is made
         * Must override this method with code that will be executed when a purchased is made
         */
        default void onPurchased() {
        }
    }
}
