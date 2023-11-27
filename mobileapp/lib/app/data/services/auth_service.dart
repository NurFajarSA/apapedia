import 'dart:io';

import 'package:get/get.dart';

class AuthService extends GetxService {
  Future<Response> signIn(
      {required String email, required String password}) async {
    return Future.value(const Response(statusCode: HttpStatus.ok));
  }

  Future<Response> register(
      {required email,
      required password,
      required name,
      required username,
      required address}) {
    return Future.value(const Response(statusCode: HttpStatus.created));
  }
}
