package contacts.xiaozuzu.github.io.contactsbook.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import contacts.xiaozuzu.github.io.contactsbook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialFragment extends Fragment {

    private Button delButton;
    private Button dialButton;
    private TextView displayNumber;

    private Map<Integer,String> numberMap = new HashMap<>();
    private int[] numberIds = {
            R.id.one,
            R.id.two,
            R.id.three,
            R.id.four,
            R.id.five,
            R.id.six,
            R.id.seven,
            R.id.eight,
            R.id.nine,
            R.id.star,
            R.id.zero,
            R.id.jing,
    };

    private Button[] numberButtons = new Button[12];

    private View contentView;
    private StringBuilder currentNumber = new StringBuilder();

    public DialFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initNumberMap();
    }

    private void initNumberMap() {
        for(int i = 0;i<numberButtons.length;i++){
            if (i == 9)
                numberMap.put(numberIds[i],"*");
            else if (i == 10)
                numberMap.put(numberIds[i],"0");
            else if (i == 11)
                numberMap.put(numberIds[i],"#");
            else {
                numberMap.put(numberIds[i],(i+1)+"");
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_dial, container, false);
        initView();
        initNumberView();
        return this.contentView;
}

    private void initNumberView() {
        NumberOnClickListener numberOnClickListener = new NumberOnClickListener();
        for (int i = 0;i<numberButtons.length;i++){
            numberButtons[i] = (Button)contentView.findViewById(numberIds[i]);
        }
        for (int i = 0;i<numberButtons.length;i++){
            numberButtons[i].setOnClickListener(numberOnClickListener);
        }
    }

    private void initView() {
        displayNumber = (TextView)contentView.findViewById(R.id.display_number);
        delButton = (Button)contentView.findViewById(R.id.del_button);
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.toString().length()>=1)
                currentNumber.deleteCharAt(currentNumber.toString().length()-1);
                displayNumber.setText(currentNumber.toString());
            }
        });
        dialButton = (Button)contentView.findViewById(R.id.dial_button);
    }


    class NumberOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            currentNumber.append(numberMap.get(v.getId()).toString());
            displayNumber.setText(currentNumber.toString());
        }
    }

}
