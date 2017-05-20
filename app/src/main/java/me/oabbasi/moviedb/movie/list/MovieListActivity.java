package me.oabbasi.moviedb.movie.list;

import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import me.oabbasi.moviedb.R;
import me.oabbasi.moviedb.databinding.ActivityMovieListBinding;

public class MovieListActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Inject
    protected MovieListViewModel movieListViewModel;

    private ActivityMovieListBinding binding;
    private DatePickerDialog datePickerDialog;
    private OpenedDialog openedDialog;
    private Calendar startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list);
        setSupportActionBar(binding.toolbar);
        initUI();
        movieListViewModel.init();
    }

    private void initUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.moviesRecyclerView.setLayoutManager(layoutManager);
        binding.moviesRecyclerView.addOnScrollListener(movieListViewModel.getEndlessScrollListener(layoutManager));
        binding.moviesRecyclerView.setAdapter(movieListViewModel.getMovieListAdapter());

        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        binding.startDate.setOnClickListener((v) -> {
            openedDialog = OpenedDialog.start;
            openDatePicker(startDate);
        });
        binding.endDate.setOnClickListener((v) -> {
            openedDialog = OpenedDialog.end;
            openDatePicker(endDate);
        });

        binding.clearDates.setOnClickListener((v) -> clearDates());
    }

    private void openDatePicker(Calendar calendar) {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        datePickerDialog.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void clearDates() {
        movieListViewModel.getMovieListAdapter().setData(new ArrayList<>());
        binding.startDate.setText("");
        binding.endDate.setText("");
        movieListViewModel.updateDates(null, null);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        String dateString = String.format("%d-%d-%d", year, month+1, dayOfMonth);
        if (openedDialog == OpenedDialog.start) {
            startDate = calendar;
            binding.startDate.setText(dateString);
        } else {
            endDate = calendar;
            binding.endDate.setText(dateString);
        }
        movieListViewModel.updateDates(startDate == null ? null : new Date(startDate.getTimeInMillis()),
                endDate == null ? null : new Date(endDate.getTimeInMillis()));
    }

    private enum OpenedDialog {
        start, end
    }
}
