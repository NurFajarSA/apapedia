import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:mobileapp/app/data/providers/api.dart';
import 'package:mobileapp/core/utils/shared_pref.dart';

class UserService {
  Future<bool> updateProfile(String name, String address, String email,
      String username, String password) async {
    var token = await TbSharedPref.getAccessToken();
    var user = TbSharedPref.getUserLogin();

    var body = <String, String>{
      'id': user!.id.toString(),
      'name': name,
      'address': address,
      'email': email,
      'username': username,
      'password': password
    };
    var headers = <String, String>{
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    var url = Uri.parse('${Api.baseUrlUser}/user/update');

    var response =
        await http.put(url, headers: headers, body: jsonEncode(body));

    if (response.statusCode == 200) {
      user.name = name;
      user.address = address;
      TbSharedPref.setUser(user);
      return true;
    } else {
      throw Exception(jsonDecode(response.body)['message']);
    }
  }

  Future<bool> topUpBalance(int amount) async {
    var token = await TbSharedPref.getAccessToken();
    var user = TbSharedPref.getUserLogin();
    var idUser = user!.id;

    var headers = <String, String>{
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    var url =
        Uri.parse('${Api.baseUrlUser}/user/$idUser/top-up?amount=$amount');

    var response = await http.put(url, headers: headers);

    if (response.statusCode == 200) {
      var user = TbSharedPref.getUserLogin();
      user!.balance = user.balance + amount;
      TbSharedPref.setUser(user);
      return true;
    } else {
      throw Exception(jsonDecode(response.body)['message']);
    }
  }

  Future<bool> deleteAccount() async {
    var token = await TbSharedPref.getAccessToken();
    var user = TbSharedPref.getUserLogin();
    var idUser = user!.id;

    var headers = <String, String>{
      'Content-Type': 'application/json',
      'Authorization': 'Bearer $token'
    };

    var url = Uri.parse('${Api.baseUrlUser}/user/delete/$idUser');

    var response = await http.delete(url, headers: headers);

    if (response.statusCode == 200) {
      TbSharedPref.removeAccessToken();
      TbSharedPref.setUserLogin(null, null);
      return true;
    } else {
      throw Exception(jsonDecode(response.body)['message']);
    }
  }
}
