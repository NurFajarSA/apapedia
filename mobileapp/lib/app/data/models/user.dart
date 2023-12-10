class User {
  final String id;
  final String name;
  final String username;
  final String email;
  final String address;
  int balance;

  User({
    required this.id,
    required this.name,
    required this.username,
    required this.email,
    required this.address,
    required this.balance,
  });

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['sub'],
      name: json['name'],
      username: json['userName'],
      email: json['email'],
      address: json['address'],
      balance: json['balance'],
    );
  }

  Map<String, dynamic> toJson() => {
        'id': id,
        'name': name,
        'username': username,
        'email': email,
        'address': address,
        'balance': balance,
      };
}
