package com.beryl.seabunne.api

class SplatnetUnauthorizedException(endpoint: String) :
    Throwable("Cannot access $endpoint: User Authentication Failed")

class SplatnetMaintenanceException(endpoint: String) :
    Throwable("Cannot access $endpoint: Splatnet is down for maintenance")

class SplatnetFailedConnectionException(endpoint: String) :
    Throwable("Cannot access $endpoint: No Response")
