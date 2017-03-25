package essyselves.bme3110.gatech.nystagmus.Presenter;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import essyselves.bme3110.gatech.nystagmus.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GifHolder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GifHolder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GifHolder extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Map<Integer, String> urlMap;

    public GifHolder() {
        // Required empty public constructor
        urlMap = new HashMap<>();
        urlMap.put(1, "C:\\Users\\Mohammed\\StudioProjects\\Nystagmus\\app\\src\\main\\res\\drawable\\n15.gif");
        urlMap.put(2, "C:\\Users\\Mohammed\\StudioProjects\\Nystagmus\\app\\src\\main\\res\\drawable\\n30.gif");
        urlMap.put(3, "C:\\Users\\Mohammed\\StudioProjects\\Nystagmus\\app\\src\\main\\res\\drawable\\n45.gif");
        urlMap.put(4, "C:\\Users\\Mohammed\\StudioProjects\\Nystagmus\\app\\src\\main\\res\\drawable\\wide n15.gif");
        urlMap.put(5, "C:\\Users\\Mohammed\\StudioProjects\\Nystagmus\\app\\src\\main\\res\\drawable\\wide n30.gif");
        urlMap.put(6, "C:\\Users\\Mohammed\\StudioProjects\\Nystagmus\\app\\src\\main\\res\\drawable\\wide n45.gif");
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GifHolder.
     */
    // TODO: Rename and change types and number of parameters
    public static GifHolder newInstance(String param1, String param2) {
        GifHolder fragment = new GifHolder();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gif_holder, container, false);
        setGif(1);
        return view;
    }

    public void setGif(int i) {
        WebView mWebView2 =  (WebView)(getView().findViewById(R.id.gif));
        mWebView2.getSettings().setJavaScriptEnabled(true);
        mWebView2.getSettings().setLoadWithOverviewMode(true);
        mWebView2.getSettings().setUseWideViewPort(true);
        mWebView2.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView2.setScrollbarFadingEnabled(true);
        mWebView2.loadDataWithBaseURL(urlMap.get(1), "<img src=\"banner5.png\" height=\"98%\" width=\"100%\"/>", "text/html", "utf-8", null);
        mWebView2.loadUrl(urlMap.get(1));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}