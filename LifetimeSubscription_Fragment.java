package tech.kushmanda.luckydays.View.ViewController.UserFragmentViews;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tech.kushmanda.luckydays.Controller.NetworkCalls.PostApi_BuyingCoin;
import tech.kushmanda.luckydays.Controller.NetworkCalls.Update_Subscription;
import tech.kushmanda.luckydays.Controller.ServiceController.Security;
import tech.kushmanda.luckydays.Model.Webservices;
import tech.kushmanda.luckydays.R;

public class LifetimeSubscription_Fragment extends Fragment {


    ImageView OneYearbuy_now_button;
    TextView Display_TextViewYearly,dollar_textYear;
    //TextView Display_Price,dollar_text;
    private BillingClient billingClientOneYear;


    String response1, des1, sku1;
    private boolean isSuccessOneYear = false;

    //Button GetPriceButton;

    String UserId;

    Handler handler = new Handler();

    Webservices webservices = new Webservices();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view;

        view = inflater.inflate(R.layout.lifetime_subscription_fragment, container, false);


        // Retrieve the position value from the bundle
        //int position = getArguments().getInt("POSITION");

        //getPrice();

        //if (position == 1){




        SharedPreferences sharename = getContext().getSharedPreferences("credentials", Context.MODE_PRIVATE);
        UserId     =  sharename.getString("UserId",null);

        GetUserDetails(UserId);

        OneYearbuy_now_button = view.findViewById(R.id.OneYearbuy_now_button);
        Display_TextViewYearly = view.findViewById(R.id.Display_TextViewYearly);

        dollar_textYear = view.findViewById(R.id.dollar_textYear);

        billingClientOneYear = BillingClient.newBuilder(getContext())
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();


        connectGooglePlayBilling();

//        billingClientOneYear = BillingClient.newBuilder(getContext())
//                .enablePendingPurchases()
//                        .setListener(new PurchasesUpdatedListener() {
//                            @Override
//                            public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
//                                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null){
//
//                                    for (Purchase purchase:list){
//                                        handlePurchase(purchase);
//                                    }
//
//                                }
//                            }
//                        }).build();


        //getPrice();



        OneYearbuy_now_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                billingClientOneYear.startConnection(new BillingClientStateListener() {
                    @Override
                    public void onBillingServiceDisconnected() {

                    }

                    @Override
                    public void onBillingSetupFinished(@NonNull BillingResult billingResult) {

                        List<QueryProductDetailsParams.Product> ProductList1 = List.of(
                                QueryProductDetailsParams.Product.newBuilder()
                                        .setProductId("one_year_subscription")
                                        .setProductType(BillingClient.ProductType.SUBS)
                                        .build()
                        );

                        QueryProductDetailsParams params2 = QueryProductDetailsParams.newBuilder()
                                .setProductList(ProductList1)
                                .build();

                        billingClientOneYear.queryProductDetailsAsync(params2, (billingResult1, productDetailsList) -> {

                            for (ProductDetails productDetails:productDetailsList){

                                String offerToken = productDetails.getSubscriptionOfferDetails().get(0).getOfferToken();
                                List < BillingFlowParams.ProductDetailsParams> productDetailsParamsList = List.of(

                                        BillingFlowParams.ProductDetailsParams.newBuilder()
                                                .setProductDetails(productDetails)
                                                .setOfferToken(offerToken)
                                                .build()

                                );

                                BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                                        .setProductDetailsParamsList(productDetailsParamsList)
                                        .build();
                                billingClientOneYear.launchBillingFlow((Activity) getContext(),billingFlowParams);

                            }


                        });

                    }
                });

            }
        });
        //}

        return view;
    }


    void connectGooglePlayBilling() {
        billingClientOneYear.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingServiceDisconnected() {
                //connectGooglePlayBilling();
            }

            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {


                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {

                    List<QueryProductDetailsParams.Product> ProductList = List.of(
                            QueryProductDetailsParams.Product.newBuilder()
                                    .setProductId("one_year_subscription")
                                    .setProductType(BillingClient.ProductType.SUBS)
                                    .build()
                    );

                    QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                            .setProductList(ProductList)
                            .build();

                    billingClientOneYear.queryProductDetailsAsync(

                            params,
                            new ProductDetailsResponseListener() {
                                @Override
                                public void onProductDetailsResponse(@NonNull BillingResult billingResult, @NonNull List<ProductDetails> productDetailsList) {

                                    Log.d("MonthlySubSize ", "Size:-" + productDetailsList.size());

                                    for (ProductDetails productDetails : productDetailsList) {

                                        response1 = productDetails.getSubscriptionOfferDetails().get(0).getPricingPhases()
                                                .getPricingPhaseList().get(0).getFormattedPrice();

                                        sku1 = productDetails.getName();
                                        String ds = productDetails.getDescription();
                                        des1 = sku1 + " " + ds + " " + " Price " + response1;

                                    }

                                }
                            }

                    );
                }
            }
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Display_Price.setText("Price: "+response );
                dollar_textYear.setText(response1);
            }
        },600);

    }


//    @Override
//    public void onResume() {
//        super.onResume();
//
//
//
//        //Toast.makeText(getContext(), "Lifetime Subscription Fragment", Toast.LENGTH_SHORT).show();
//    }

    private final PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
        @Override
        public void onPurchasesUpdated(@NonNull BillingResult billingResult, List<Purchase> Purchase) {
            // To be implemented in a later section.

            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && Purchase != null){

                for (Purchase purchase:Purchase){
                    handlePurchase(purchase);
                }

            }else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED){
                //Toast.makeText(getActivity(), "Already One year Subscribed", Toast.LENGTH_SHORT).show();
                Display_TextViewYearly.setText("Already 1 year Subscribed");
                OneYearbuy_now_button.setVisibility(View.INVISIBLE);
            } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED) {

                //Toast.makeText(getActivity(), "Featured is not supported", Toast.LENGTH_SHORT).show();
                Display_TextViewYearly.setText("Featured is not supported");

            }else {

                Log.d("ErrorLifeTime","Error:-> "+billingResult.getDebugMessage());
                //Toast.makeText(getActivity(), "Error:-> "+billingResult.getDebugMessage(), Toast.LENGTH_LONG).show();
            }

        }
    };


    private void handlePurchase(Purchase purchase) {

        ConsumeParams consumeParams =
                ConsumeParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();

        ConsumeResponseListener listener = (billingResult, purchaseToken) -> {
            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                // Handle the success of the consume operation.

                //GiveUserOneYearCoin(purchase);

            }
        };

        billingClientOneYear.consumeAsync(consumeParams, listener);

        GiveUserOneYearCoin(purchase);


    }


    private void GiveUserOneYearCoin(Purchase purchase){

        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED){
            if (!verifyValidSignature(purchase.getOriginalJson(), purchase.getSignature())){
                Toast.makeText(getActivity(), "Error : invalid Purchase", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!purchase.isAcknowledged()){
                AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();
                billingClientOneYear.acknowledgePurchase(acknowledgePurchaseParams,acknowledgePurchaseResponseListenerOneYear);
                Toast.makeText(getActivity(), "One Year Subscription Done", Toast.LENGTH_SHORT).show();

                //Here push the confirmation code to server

                // I was commented this code please recheck again later start

                Update_Subscription update_subscription = new Update_Subscription();
                update_subscription.UpdateSubscription_Users(getContext(),UserId,12);


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        PostApi_BuyingCoin postApi_buyingCoin = new PostApi_BuyingCoin();
                        postApi_buyingCoin.BuyCoin_SellingCoin(getContext(),UserId,"50","1","1","0",purchase.getPurchaseToken(), purchase.getOrderId(), String.valueOf(purchase.getPurchaseTime()));
                        //Refresh();
                    }
                }, 1000);
                // I was commented this code please recheck again later end

                Display_TextViewYearly.setText("One Year Subscribed");
                isSuccessOneYear = true;
                OneYearbuy_now_button.setVisibility(View.INVISIBLE);
            }else {
                Toast.makeText(getActivity(), "Already One Year Subscribed", Toast.LENGTH_SHORT).show();
                Display_TextViewYearly.setText("Already One Year Subscribed");
                OneYearbuy_now_button.setVisibility(View.INVISIBLE);
            }

        }
        else if (purchase.getPurchaseState() == Purchase.PurchaseState.PENDING){
            Toast.makeText(getActivity(), "Subscription Pending", Toast.LENGTH_SHORT).show();
        }
        else if (purchase.getPurchaseState() == Purchase.PurchaseState.UNSPECIFIED_STATE){
            Toast.makeText(getActivity(), "Unspecified State", Toast.LENGTH_SHORT).show();
        }

    }

    AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListenerOneYear = new AcknowledgePurchaseResponseListener() {
        @Override
        public void onAcknowledgePurchaseResponse(@NonNull BillingResult billingResult) {

            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
                Toast.makeText(getActivity(), "One year Subscribed!", Toast.LENGTH_SHORT).show();
                isSuccessOneYear = true;
            }

        }
    };



    private boolean verifyValidSignature(String signedData, String signature){

        try {
            String base64Key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwbeU7IE6XNLStPMTuv11311g3bTQJ3GvcL0XmQ+VSIk/z8Au4cv9SnmFffD6H/rJzwij/aQZcbO8MUUASgMMGGEVQ1QVp2D2SyPDhn3KmvqpOpmzxqpl8tQ4TnT6CdrFQ5BWBxge68POgHMBLHZvu2eY/46IgKvT1Rhb5LNwYu6w45opjWsMsTRSXjzGNselRFOwChG2zgmzhRS5Met1TIUIM2aQ8kvehGDPrDtV7TWSSV5rNU6DONd6RUgySH+8qCBTbzZC/Xdg2cnvNKSOYov57QAP28RwnpMV6bV2vAaQq6ZuzNIzRy+279pw6FPToHJ98olEqeCInoeJfSzeHwIDAQAB";
            return Security.verifyPurchase(base64Key,signedData,signature);
        }catch (IOException e){

            e.printStackTrace();

        }
        return false;
    }




    @Override
    public void onDestroy() {
        super.onDestroy();

        if (billingClientOneYear!=null){
            billingClientOneYear.endConnection();
        }

    }



    private void GetUserDetails(String UserToken){

        String GetUser = webservices.GetUserDetails;



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, GetUser,null,  new Response.Listener<JSONObject>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONObject response) {

                //System.out.println("FCM_Response" + response);
                Log.d("ResponseFROM_LifeTimeSubs", String.valueOf(response));

                try {



                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i =0; i<jsonArray.length(); i++){

                        JSONObject dataobj = jsonArray.getJSONObject(i);


                        //boolean ads_remove_status = dataobj.getBoolean("ads_remove_status");
                        boolean subscription_status = dataobj.getBoolean("subscription_status");
                        String subscription_type_in_month = dataobj.getString("subscription_type_in_month");


                        if (subscription_status == true && subscription_type_in_month.equals("12")) {


                            Display_TextViewYearly.setText("One year Subscribed");
                            OneYearbuy_now_button.setVisibility(View.INVISIBLE);


                        } else if (subscription_status == false && !subscription_type_in_month.equals("12")) {


                            OneYearbuy_now_button.setVisibility(View.VISIBLE);


                        }


                    }

                }catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                System.out.println("Error in LifeTimeSubscription: "+error);
                //progress1.dismiss();
            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("Content-Type","application/json");
                params.put("Authorization",UserId);
                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        requestQueue.add(jsonObjectRequest);
        requestQueue.getCache().clear();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

//        connectGooglePlayBilling();
//        if (billingClientOneYear!=null){
//            billingClientOneYear.endConnection();
//        }
        if (billingClientOneYear!=null){
            billingClientOneYear.endConnection();
        }

    }
}