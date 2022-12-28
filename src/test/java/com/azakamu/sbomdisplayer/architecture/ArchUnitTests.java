package com.azakamu.sbomdisplayer.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

import com.azakamu.sbomdisplayer.SbomDisplayerApplication;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author janlingen
 */
@AnalyzeClasses(
    packagesOf = SbomDisplayerApplication.class,
    importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchUnitTests {

  // General coding rules
  // ----------------------------------------------------------------------------------------------


  // No Field should be injected with @Autowired, to keep up the Testability
  @ArchTest
  static final ArchRule noMembersShouldBeAutowired = GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

  // No Class should throw just an Exception, better throw something like NoSuchElementException
  @ArchTest
  static final ArchRule noGenericException = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

  // No Class should use something like System.out..
  @ArchTest
  static final ArchRule noAccessToStandardStreams = GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

  // In modern projects it is common to use Log4j or Logback
  @ArchTest
  static final ArchRule noJavaUtilLogging = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

  // In modern projects it is common to use Java.time
  @ArchTest
  static final ArchRule noJodaTime = GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME;

  // Onion architecture preset
  // ----------------------------------------------------------------------------------------------

  @ArchTest
  static final ArchRule onion =
      onionArchitecture()
          .withOptionalLayers(true)
          .domainModels("..domain..")
          .applicationServices("..application..")
          .adapter("web", "..adapters.web..")
          .adapter("database", "..adapters.database..");

  // Custom rules
  // ----------------------------------------------------------------------------------------------

  // Classes ending with Dao should reside in package dataaccess
  @ArchTest
  static final ArchRule daosMustResideInDataaccessPackage =
      classes().that().haveNameMatching(".*DAO").should().resideInAPackage("..dataaccess..")
          .as("DataAccessObjects should reside in a package '..dataaccess..'");

  // No interface should have Interface in its name
  @ArchTest
  static final ArchRule interfacesShouldNotHaveSimpleClassNamesContainingTheWordInterface =
      noClasses().that().areInterfaces().should().haveSimpleNameContaining("Interface")
          .as("Interface names should not contain the String Interface");


  // Fields which are declared in classes annotated with @Controller should be private
  @ArchTest
  static final ArchRule fieldsShouldBePrivate =
      fields()
          .that()
          .areDeclaredInClassesThat()
          .areAnnotatedWith(Controller.class)
          .should()
          .bePrivate()
          .as("Controller fields should not be accessible by any other object");

  // Classes that are top level classes and not interfaces
  // and reside in package services should be annotated with @Service
  @ArchTest
  static final ArchRule serviceClassAnnotation =
      classes()
          .that()
          .areTopLevelClasses()
          .and()
          .areNotInterfaces()
          .and()
          .resideInAPackage("..application.services..")
          .should()
          .beMetaAnnotatedWith(Service.class)
          .as("Services need to be annotated with @Service to be loaded into context");

  // Classes that reside in package service should have the suffix Service
  @ArchTest
  static final ArchRule serviceClassSuffix =
      classes()
          .that().resideInAPackage("..application.services..")
          .and().areAnnotatedWith(Service.class)
          .should().haveSimpleNameEndingWith("Service")
          .as("The end of the name of a service class should be Service");

  // Classes that are top level classes and not interfaces
  // and reside in package controller should be annotated with @Controller
  @ArchTest
  static final ArchRule controllerClassAnnotation =
      classes()
          .that()
          .areTopLevelClasses()
          .and()
          .areNotInterfaces()
          .and()
          .resideInAPackage("..adapters.web..")
          .should()
          .beMetaAnnotatedWith(Controller.class).orShould()
          .beMetaAnnotatedWith(RestController.class);

  // Classes that reside in package controller should have the suffix Service
  @ArchTest
  static final ArchRule controllerClassSuffix =
      classes()
          .that().resideInAPackage("..adapters.web..")
          .and().areAnnotatedWith(Controller.class)
          .should().haveSimpleNameEndingWith("Controller")
          .as("The end of the name of a controller class should be Controller");

  // Classes that are top level classes and reside in package application.repositories
  // should be interfaces
  @ArchTest
  static final ArchRule shouldBeInterfaces =
      classes()
          .that()
          .areTopLevelClasses()
          .and()
          .resideInAPackage("..application.repositories..")
          .should()
          .beInterfaces();

  // Classes that are top level classes and reside in package database.implementation
  // should implement interfaces residing in package application.repositories
  @ArchTest
  static final ArchRule implementingInterfaces =
      classes()
          .that()
          .areTopLevelClasses()
          .and()
          .resideInAPackage("..adapters.database.implementation")
          .should()
          .implement(JavaClass.Predicates.resideInAPackage("..application.repositories.."));

}