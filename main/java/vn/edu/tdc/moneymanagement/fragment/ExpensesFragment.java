package vn.edu.tdc.moneymanagement.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vn.edu.tdc.moneymanagement.R;
import vn.edu.tdc.moneymanagement.adapter.ExpenseAdapter;
import vn.edu.tdc.moneymanagement.adapter.ListAdapter;
import vn.edu.tdc.moneymanagement.adapter.SpendingAdapter;
import vn.edu.tdc.moneymanagement.database.MyDatabase;
import vn.edu.tdc.moneymanagement.model.SpendingAccount;

public class ExpensesFragment extends Fragment {


    private ArrayList<SpendingAccount> spendingAccounts;
    private MyDatabase myDatabase;
    private SpendingAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(vn.edu.tdc.moneymanagement.R.layout.expense_fragment, container, false);

        //innit database
        myDatabase = new MyDatabase(getContext());

        spendingAccounts = new ArrayList<>();
        spendingAccounts = myDatabase.getAllSpendingAccounts();
        Log.d("test", spendingAccounts.size() + "");

        RecyclerView expenseRecyclerView = fragment.findViewById(R.id.recyclerViewExpense);
        AppCompatButton btnAdd = fragment.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSpendingFragment fragment = new AddSpendingFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_view_tag, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        adapter = new SpendingAdapter(getContext(), spendingAccounts);
        expenseRecyclerView.setAdapter(adapter);


        return fragment;
    }
}
