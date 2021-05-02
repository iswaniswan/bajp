package com.iswan.main.movflix.utils

import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.rest.ApiClient
import com.iswan.main.movflix.data.rest.ApiMovies

object Injectors {

    val repository: Repository = Repository(ApiMovies(ApiClient))

}



