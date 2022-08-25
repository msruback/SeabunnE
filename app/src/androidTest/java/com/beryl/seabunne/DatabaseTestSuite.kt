package com.beryl.seabunne

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    GearDatabaseTests::class,
    SalmonRunDatabaseTests::class,
    ScheduleDatabaseTests::class,
    WeaponDatabaseTests::class
)
class DatabaseTestSuite
