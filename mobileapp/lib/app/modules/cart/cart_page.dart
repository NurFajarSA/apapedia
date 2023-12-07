import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/cart/cart_controller.dart';
import 'package:mobileapp/app/modules/cart/widgets/cart_item.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class CartPage extends StatelessWidget {
  const CartPage({super.key});

  @override
  Widget build(BuildContext context) {
    final cartController = Get.find<CartController>();
    return Scaffold(
      appBar: AppBar(
        title: Text('Cart'),
      ),
      body: ListView(
        children: [
          Container(
              margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
              child: CartItem())
        ],
      ),
      bottomNavigationBar: Container(
        padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
        height: 60,
        decoration: BoxDecoration(
          color: MyColors.white,
          boxShadow: BoxShadowDecorator().defaultShadow(context),
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            SizedBox(
              width: MediaQuery.of(context).size.width * 0.4,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    'Total',
                    style: Theme.of(context).textTheme.labelLarge?.copyWith(
                          color: MyColors.black,
                        ),
                  ),
                  Text(
                    'Rp 200.000',
                    style: Theme.of(context).textTheme.labelLarge?.copyWith(
                        color: MyColors.primary, fontWeight: FontWeight.bold),
                  ),
                ],
              ),
            ),
            SizedBox(
              width: MediaQuery.of(context).size.width * 0.4,
              child: PrimaryButton(
                onPressed: () {
                  cartController.checkout();
                },
                child: Center(
                  child: Text(
                    'Checkout',
                    style: Theme.of(context).textTheme.labelLarge?.copyWith(
                          color: MyColors.white,
                        ),
                  ),
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
