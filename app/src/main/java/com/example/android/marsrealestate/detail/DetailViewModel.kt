/*
 *  Copyright 2018, The Android Open Source Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.android.marsrealestate.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.android.marsrealestate.network.MarsProperty
import com.example.android.marsrealestate.R

/**
 * The [ViewModel] that is associated with the [DetailFragment].
 */
class DetailViewModel(marsProperty: MarsProperty, app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData(marsProperty)

    val selectedProperty: LiveData<MarsProperty>
        get() = _selectedProperty

    val propertyPrice = Transformations.map(selectedProperty) {
        val stringRes = if (it.isRental)
            R.string.display_price_monthly_rental
        else
            R.string.display_price
        app.applicationContext.getString(stringRes, it.price)
    }

    val propertyType = Transformations.map(selectedProperty) {
        val typeRes = if (it.isRental)
            R.string.type_rent
        else
            R.string.type_sale
        val secondArg = app.applicationContext.getString(typeRes)
        app.applicationContext.getString(R.string.display_type, secondArg)
    }
}