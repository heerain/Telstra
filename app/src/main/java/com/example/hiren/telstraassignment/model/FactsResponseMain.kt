package com.example.hiren.telstraassignment.model

import com.google.gson.annotations.Expose

data class FactsResponseMain(@Expose var title : String, var rows: ArrayList<FactsResponse>)
