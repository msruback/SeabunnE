package com.beryl.seabunsource.api.exceptions

class SplatnetUnauthorizedException(endpoint: String) :
    Throwable("Cannot access $endpoint: User Authentication Failed")

class SplatnetMaintenanceException(endpoint: String) :
    Throwable("Cannot access $endpoint: Splatnet is down for maintenance")