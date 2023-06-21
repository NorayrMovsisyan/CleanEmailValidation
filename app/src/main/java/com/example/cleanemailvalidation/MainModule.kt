package com.example.cleanemailvalidation

import com.example.cleanemailvalidation.presentation.MainViewModel
import com.example.cleanemailvalidation.use_case.ValidateEmail
import com.example.cleanemailvalidation.use_case.ValidatePassword
import com.example.cleanemailvalidation.use_case.ValidateRepeatedPassword
import com.example.cleanemailvalidation.use_case.ValidateTerms
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory { ValidateEmail() }
    factory { ValidatePassword() }
    factory { ValidateRepeatedPassword() }
    factory { ValidateTerms() }
}

val viewModelModule = module {
    viewModel { MainViewModel(get(), get(), get(), get()) }
}