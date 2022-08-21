package com.diatomicsoft.fruits.util

import java.io.IOException

class NoInternetExceptions(message: String) : IOException(message)
class ApiExceptions(message: String) : IOException(message)