package com.azhel.gogonedemo.di

import androidx.room.Room
import com.azhel.gogonedemo.data.IrregularVerbsDataBase
import com.azhel.gogonedemo.data.RepositoryImpl
import com.azhel.gogonedemo.data.dao.VerbsDao
import com.azhel.gogonedemo.domain.Repository
import com.azhel.gogonedemo.domain.VerbAnswerValidator
import com.azhel.gogonedemo.domain.usecases.GetVerbsCountUseCase
import com.azhel.gogonedemo.domain.usecases.GetVerbsUseCase
import com.azhel.gogonedemo.domain.usecases.UpdateVerbsToLearnUseCase
import com.azhel.gogonedemo.presentation.viewmodels.LearnVerbsSetUpViewModel
import com.azhel.gogonedemo.presentation.viewmodels.LearnVerbsViewModel
import com.azhel.gogonedemo.presentation.viewmodels.VerbsListSetUpViewModel
import com.azhel.gogonedemo.presentation.viewmodels.VerbsListViewModel
import com.azhel.gogonedemo.presentation.viewmodels.VerbsUnderLearnViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val irregularVerbsModule = module {

    single {
        Room.databaseBuilder(
            androidContext().applicationContext,
            IrregularVerbsDataBase::class.java,
            "irregular_verbs_database"
        )
            .createFromAsset("verbs.db")
            .build()
    }

    single<VerbsDao> {
        get<IrregularVerbsDataBase>().verbsDao()
    }

    single<Repository> {
        RepositoryImpl(get())
    }

    viewModel<VerbsListViewModel> {
        VerbsListViewModel(get(), get(), get(), get())
    }

    viewModel<VerbsUnderLearnViewModel> {
        VerbsUnderLearnViewModel(get(), get(), get())
    }

    viewModel<LearnVerbsViewModel> {
        LearnVerbsViewModel(get(), get(), get())
    }

    viewModel<LearnVerbsSetUpViewModel> {
        LearnVerbsSetUpViewModel(get(), get())
    }

    viewModel<VerbsListSetUpViewModel> {
        VerbsListSetUpViewModel(get(), get(), get())
    }

    single { GetVerbsCountUseCase(get()) }

    single { GetVerbsUseCase(get()) }

    single { UpdateVerbsToLearnUseCase(get()) }

    single { VerbAnswerValidator() }
}