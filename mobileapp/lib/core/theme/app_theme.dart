import 'package:flutter/material.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:mobileapp/core/theme/text_theme.dart';

class AppTheme {
  static ThemeData apapediaTheme = ThemeData(
    brightness: Brightness.light,
    useMaterial3: true,
    colorScheme: ColorScheme.fromSwatch(
      brightness: Brightness.light,
      primarySwatch: MaterialColorGenerator.from(ColorsTheme.primary),
      accentColor: MaterialColorGenerator.from(ColorsTheme.accent),
      errorColor: MaterialColorGenerator.from(ColorsTheme.error),
      backgroundColor: MaterialColorGenerator.from(ColorsTheme.background),
      cardColor: MaterialColorGenerator.from(ColorsTheme.surface),
    ),
    appBarTheme: const AppBarTheme(
      backgroundColor: ColorsTheme.background,
      elevation: 0,
      centerTitle: true,
      titleTextStyle: TextStyle(
        fontSize: 20,
        fontWeight: FontWeight.w500,
        letterSpacing: 0.15,
        fontFamily: 'Poppins',
        color: ColorsTheme.secondary,
      ),
      iconTheme: IconThemeData(
        color: ColorsTheme.primary,
      ),
    ),
    bottomNavigationBarTheme: const BottomNavigationBarThemeData(
      backgroundColor: ColorsTheme.background,
      elevation: 0,
      selectedItemColor: ColorsTheme.primary,
      unselectedItemColor: ColorsTheme.greyLight,
      selectedLabelStyle: TextStyle(
        fontSize: 12,
        fontWeight: FontWeight.w500,
        letterSpacing: 0.4,
        fontFamily: 'Poppins',
      ),
      unselectedLabelStyle: TextStyle(
        fontSize: 12,
        fontWeight: FontWeight.w400,
        letterSpacing: 0.4,
        fontFamily: 'Poppins',
      ),
    ),
    elevatedButtonTheme: ElevatedButtonThemeData(
      style: ElevatedButton.styleFrom(
        backgroundColor: ColorsTheme.primary,
        foregroundColor: ColorsTheme.surface,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(8),
        ),
        textStyle: const TextStyle(
          fontSize: 14,
          fontWeight: FontWeight.w500,
          letterSpacing: 0.4,
          fontFamily: 'Poppins',
        ),
      ),
    ),
    textButtonTheme: TextButtonThemeData(
      style: TextButton.styleFrom(
        backgroundColor: ColorsTheme.primary,
        textStyle: const TextStyle(
          fontSize: 14,
          fontWeight: FontWeight.w500,
          letterSpacing: 0.4,
          fontFamily: 'Poppins',
        ),
      ),
    ),
    outlinedButtonTheme: OutlinedButtonThemeData(
      style: OutlinedButton.styleFrom(
        backgroundColor: ColorsTheme.primary,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(8),
        ),
        textStyle: const TextStyle(
          fontSize: 14,
          fontWeight: FontWeight.w500,
          letterSpacing: 0.4,
          fontFamily: 'Poppins',
        ),
      ),
    ),
    textTheme: TextThemeApapedia.textThemeApapedia,
    shadowColor: ColorsTheme.greyLighter,
  );
}
