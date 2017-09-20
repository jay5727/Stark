package com.example.jay.stark;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jay.stark.models.DrivingHole;
import com.example.jay.stark.models.HSSStandard;
import com.example.jay.stark.models.MaxCutChoice;
import com.example.jay.stark.models.PitchSelection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.Sort;

/**
 * Created by Jay on 16-08-2017.
 */
public class SawParameterFragment extends Fragment {

    @InjectView(R.id.spinnerDiameter)
    Spinner spinnerDiameter;

    @InjectView(R.id.spinnerThicknessSP)
    Spinner spinnerThicknessSP;

    @InjectView(R.id.spinnerBore)
    Spinner spinnerBore;

    @InjectView(R.id.spinnerMaterial)
    Spinner spinnerMaterial;

    @InjectView(R.id.spinnerProfile)
    Spinner spinnerProfile;

    @InjectView(R.id.spinnerThicknessMP)
    Spinner spinnerThicknessMP;




    @InjectView(R.id.tvMaxCutCap)
    TextView tvMaxCutCap;

    @InjectView(R.id.tvCodeValue)
    TextView tvCodeValue;

    @InjectView(R.id.tvDrivingHolesValue)
    TextView tvDrivingHolesValue;


    @InjectView(R.id.btnSearch)
    AppCompatButton btnSearch;

    @OnClick(R.id.btnSearch)
    public void performSearch() {
        if (lstMaxCutChoice != null && lstMaxCutChoice.size() > 0) {
            if (curIndexDiameter > 0 && curIndexThickness > 0 && curIndexBore > 0)
                tvMaxCutCap.setText(lstMaxCutChoice.get(curIndexDiameter).getCuttingCapMm());
        }
        if (selectedBoreValue != null) {
            if (lstDrivingHole != null) {
                if (lstDrivingHole.size() > 0) {
                    for (int i = 0; i < lstDrivingHole.size(); i++) {
                        if (selectedBoreValue.equals(lstDrivingHole.get(i).getBore())) {
                            //set the text & break
                            tvCodeValue.setText(lstDrivingHole.get(i).getCode());
                            tvDrivingHolesValue.setText(lstDrivingHole.get(i).getDholes());
                            break;
                        }
                    }
                }
            }
        }
    }


    //private Context ctx = this.getActivity().getApplicationContext ();
   /* private Spinner spinnerDiameter;
    private Spinner spinnerThickness;
    private Spinner spinnerBore;
    private TextView tvMaxCutCap*/;
    private List<MaxCutChoice> lstMaxCutChoice = null;
    private List<DrivingHole> lstDrivingHole = null;
    private List<HSSStandard> lstHSSStandard = null;
    private List<PitchSelection> lstPitchSelection = null;


    private int curIndexDiameter = 0;
    private int curIndexThickness = 0;
    private int curIndexBore = 0;

    private String selectedDiameterValue = null;
    private String selectedThicknessValue = null;
    private String selectedBoreValue = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.frag_saw_parameter, container, false);
        ButterKnife.inject(this, rootView);

        //findViewsById(rootView); no need

        lstMaxCutChoice = prepareMaxCutChoiceData(); //19 + 1 to get MaxCutCap value on Search click...!

        lstDrivingHole = prepareDrivingHoleData();//10 + 1
        lstHSSStandard = prepareHSSStandardData();//191 + 1
        lstPitchSelection = preparePitchSelectionData();//12+ 1
        Realm.init(getActivity());//MANDATATORY
          storeListsToDatabase(lstDrivingHole,lstHSSStandard/*,lstPitchSelection*/);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(),
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.Diameter));
        adapter.setDropDownViewResource(R.layout.spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDiameter.setAdapter(adapter);

       /* ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (getContext(),
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.Thickness));
        adapter2.setDropDownViewResource(R.layout.spinner_item);
        //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerThickness.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                (getContext(),
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.Material));
        adapter3.setDropDownViewResource(R.layout.spinner_item);
        //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBore.setAdapter(adapter3);
*/

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                (getContext(),
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.Material));
        adapter3.setDropDownViewResource(R.layout.spinner_item);
        //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMaterial.setAdapter(adapter3);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>
                (getContext(),
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.Profile));
        adapter4.setDropDownViewResource(R.layout.spinner_item);
        //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProfile.setAdapter(adapter4);





        spinnerDiameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view,
                                       int position,
                                       long l) {

                tvMaxCutCap.setText("");
                tvCodeValue.setText("");
                tvDrivingHolesValue.setText("");
                selectedBoreValue=null;
                spinnerThicknessSP.setAdapter(null);
                spinnerBore.setAdapter(null);
                selectedDiameterValue = spinnerDiameter.getSelectedItem().toString();
                if (!(selectedDiameterValue.equals("--Select--"))) {
                    filterThicknessValuesBasedOnDiameter(selectedDiameterValue);
                }
                // ((TextView) spinnerDiameter.getSelectedView()).setTextColor(getResources().getColor(R.color.black));
                //grab the index & save it so that we can loop through list later on search button.....!
                curIndexDiameter = position;
                //change state of lower 2 controls...!
                curIndexThickness = 0;
                curIndexBore = 0;
                //spinnerThickness.setSelection(0);

                //spinnerBore.setSelection(0);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Toast.makeText(, "Nothing is selected", Toast.LENGTH_LONG).show();

            }
        });


        spinnerThicknessSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view,
                                       int position,
                                       long l) {

                spinnerBore.setAdapter(null);
                selectedBoreValue=null;
                tvMaxCutCap.setText("");
                tvCodeValue.setText("");
                tvDrivingHolesValue.setText("");
                selectedThicknessValue = spinnerThicknessSP.getSelectedItem().toString();
                if (!(selectedThicknessValue.equals("--Select--"))) {
                    filterBoreValuesBasedOnThickness(selectedDiameterValue, selectedThicknessValue);
                }

                // ((TextView) spinnerDiameter.getSelectedView()).setTextColor(getResources().getColor(R.color.black));

                //grab the index & save it so that we can loop through list later on search button.....!
                curIndexThickness = position;

                //change state of lower control...!
                curIndexBore = 0;
                spinnerBore.setSelection(0);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Toast.makeText(, "Nothing is selected", Toast.LENGTH_LONG).show();

            }
        });


        spinnerBore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view,
                                       int position,
                                       long l) {

                // ((TextView) spinnerDiameter.getSelectedView()).setTextColor(getResources().getColor(R.color.black));
                tvMaxCutCap.setText("");
                tvCodeValue.setText("");
                tvDrivingHolesValue.setText("");
                selectedBoreValue = spinnerBore.getSelectedItem().toString();

                //grab the index & save it so that we can loop through list later on search button.....!
                curIndexBore = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Toast.makeText(, "Nothing is selected", Toast.LENGTH_LONG).show();

            }
        });

        spinnerMaterial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view,
                                       int position,
                                       long l) {

                // ((TextView) spinnerDiameter.getSelectedView()).setTextColor(getResources().getColor(R.color.black));
               /* tvMaxCutCap.setText("");
                tvCodeValue.setText("");
                tvDrivingHolesValue.setText("");
                selectedBoreValue = spinnerBore.getSelectedItem().toString();

                //grab the index & save it so that we can loop through list later on search button.....!
                curIndexBore = position;*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Toast.makeText(, "Nothing is selected", Toast.LENGTH_LONG).show();

            }
        });


        spinnerProfile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view,
                                       int position,
                                       long l) {

                // ((TextView) spinnerDiameter.getSelectedView()).setTextColor(getResources().getColor(R.color.black));


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Toast.makeText(, "Nothing is selected", Toast.LENGTH_LONG).show();

            }
        });

        spinnerThicknessMP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view,
                                       int position,
                                       long l) {

                // ((TextView) spinnerDiameter.getSelectedView()).setTextColor(getResources().getColor(R.color.black));


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Toast.makeText(, "Nothing is selected", Toast.LENGTH_LONG).show();

            }
        });

        return rootView;
    }

    void storeListsToDatabase(final List<DrivingHole> lstDrivingHole,
                              final List<HSSStandard> lstHSSStandard/*,
                              final List<PitchSelection> lstPitchSelection*/) {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    RealmList<DrivingHole> _DrivingHoleList = new RealmList<>();
                    RealmList<HSSStandard> _HSSStandardList = new RealmList<>();
                    //RealmList<PitchSelection> _PitchSelectionList = new RealmList<>();

                    _DrivingHoleList.addAll(lstDrivingHole);
                    _HSSStandardList.addAll(lstHSSStandard);
                    // _PitchSelectionList.addAll(lstPitchSelection);

                    realm.insert(_DrivingHoleList); // <-- insert unmanaged to Realm
                    realm.insert(_HSSStandardList); // <-- insert unmanaged to Realm
                    //realm.insert(_PitchSelectionList); // <-- insert unmanaged to Realm

                }

            });
        } catch (Exception ex) {
            String s = ex.getMessage();
        }

    }

    private void filterBoreValuesBasedOnThickness(String selectedDiameterValue, String selectedThicknessValue) {
        Realm.init(getActivity());
        Realm realm = Realm.getDefaultInstance();

        ArrayList<HSSStandard> distinctBore = new ArrayList(realm.where(HSSStandard.class)
                .equalTo("dia", selectedDiameterValue)
                .equalTo("thickness", selectedThicknessValue)
                .distinct("bore").sort("bore", Sort.ASCENDING)
        );

        if (distinctBore != null) {
            if (distinctBore.size() > 0) {
                List<String> boreList = new ArrayList<>();
                boreList.add("--Select--");
                for (int i = 0; i < distinctBore.size(); i++) {
                    boreList.add(distinctBore.get(i).getBore());
                }
                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                        (getContext(),
                                android.R.layout.simple_spinner_item,
                                //getResources().getStringArray(R.array.Material));
                                boreList);
                adapter3.setDropDownViewResource(R.layout.spinner_item);
                //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerBore.setAdapter(adapter3);
                //comment

            }
        }
    }

    private void filterThicknessValuesBasedOnDiameter(String selectedDiameterValue) {
        Realm.init(getActivity());
        Realm realm = Realm.getDefaultInstance();

        ArrayList<HSSStandard> distinctThickness = new ArrayList(realm.where(HSSStandard.class)
                //.findAllSorted("")
                .equalTo("dia", selectedDiameterValue)
                .distinct("thickness").sort("thickness", Sort.ASCENDING)
        );

        if (distinctThickness != null) {
            if (distinctThickness.size() > 0) {
                List<String> thicknessList = new ArrayList<>();
                thicknessList.add("--Select--");
                for (int i = 0; i < distinctThickness.size(); i++) {
                    thicknessList.add(distinctThickness.get(i).getThickness());
                }

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                        (getContext(),
                                android.R.layout.simple_spinner_item,
                                //getResources().getStringArray(R.array.Thickness));
                                thicknessList);

                adapter2.setDropDownViewResource(R.layout.spinner_item);
                //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerThicknessSP.setAdapter(adapter2);

            }
        }



   /*     ArrayList<HSSStandard> items = new ArrayList(realm.where(HSSStandard.class)
                .beginGroup()
                .equalTo("dia", selectedDiameterValue)
                .endGroup().
                        findAll());*/

        // ArrayList<HSSStandard> distinctThickness = new ArrayList(realm.where(HSSStandard.class).distinct("thickness"));
        //ArrayList<HSSStandard> distinctBore = new ArrayList(realm.where(HSSStandard.class).distinct("bore"));




        /*if (items != null)
        {
            if (items.size() > 0) {
                List<String> thicknessList = new ArrayList<>();
                List<String> boreList = new ArrayList<>();
                for (int i = 0; i < items.size(); i++) {
                    thicknessList.add(items.get(i).getThickness());
                    boreList.add(items.get(i).getBore());
                }

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                        (getContext(),
                                android.R.layout.simple_spinner_item,
                                //getResources().getStringArray(R.array.Thickness));
                                thicknessList);

                adapter2.setDropDownViewResource(R.layout.spinner_item);
                //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerThickness.setAdapter(adapter2);

                ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                        (getContext(),
                                android.R.layout.simple_spinner_item,
                                //getResources().getStringArray(R.array.Material));
                                boreList);
                adapter3.setDropDownViewResource(R.layout.spinner_item);
                //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerBore.setAdapter(adapter3);

            }
        }*/
    }

    /*private void findViewsById(View view) {
        spinnerDiameter = (Spinner) view.findViewById(R.id.spinnerDiameter);
        spinnerThicknessSP = (Spinner) view.findViewById(R.id.spinnerThickness);
        spinnerBore = (Spinner) view.findViewById(R.id.spinnerBore);
        tvMaxCutCap = (TextView) view.findViewById(R.id.tvMaxCutCap);
    }*/


    public List<MaxCutChoice> prepareMaxCutChoiceData() {
        try {
            List<MaxCutChoice> maxCutChoiceList = new ArrayList<>();
            InputStream stream = getActivity().getAssets().open("MaxCutChoice.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String data = builder.toString();
            maxCutChoiceList = new Gson().fromJson(data, new TypeToken<List<MaxCutChoice>>() {
            }.getType());
            return maxCutChoiceList;
        } catch (Exception e) {
            //java.lang.IllegalStateException: Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $
            //dont keep root key...
            e.printStackTrace();
        }
        return null;
    }

    public List<DrivingHole> prepareDrivingHoleData() {
        try {
            List<DrivingHole> drivingHoleList = new ArrayList<>();
            InputStream stream = getActivity().getAssets().open("DrivingHoles.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String data = builder.toString();
            drivingHoleList = new Gson().fromJson(data, new TypeToken<List<DrivingHole>>() {
            }.getType());
            return drivingHoleList;
        } catch (Exception e) {
            //java.lang.IllegalStateException: Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $
            //dont keep root key...
            e.printStackTrace();
        }
        return null;
    }

    public List<HSSStandard> prepareHSSStandardData() {
        try {
            List<HSSStandard> HSSStandardList = new ArrayList<>();
            InputStream stream = getActivity().getAssets().open("HSSStandard.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String data = builder.toString();
            HSSStandardList = new Gson().fromJson(data, new TypeToken<List<HSSStandard>>() {
            }.getType());
            return HSSStandardList;
        } catch (Exception e) {
            //java.lang.IllegalStateException: Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $
            //dont keep root key...
            e.printStackTrace();
        }
        return null;
    }

    public List<PitchSelection> preparePitchSelectionData() {
        try {
            List<PitchSelection> PitchSelectionList = new ArrayList<>();
            InputStream stream = getActivity().getAssets().open("PitchSelection.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String data = builder.toString();
            PitchSelectionList = new Gson().fromJson(data, new TypeToken<List<HSSStandard>>() {
            }.getType());
            return PitchSelectionList;
        } catch (Exception e) {
            //java.lang.IllegalStateException: Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 2 path $
            //dont keep root key...
            e.printStackTrace();
        }
        return null;
    }
}
