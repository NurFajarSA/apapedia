import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class CheckoutPage extends StatelessWidget {
  const CheckoutPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Checkout'),
      ),
      bottomNavigationBar: Container(
        padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
        height: 60,
        decoration: BoxDecoration(
          color: Colors.white,
          boxShadow: BoxShadowDecorator().defaultShadow(context),
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            // button buy
            Expanded(
              child: SecondaryButton(
                onPressed: () {
                  Get.back();
                },
                child: Center(
                  child: Text(
                    'Cancel',
                    style: Theme.of(context).textTheme.labelLarge?.copyWith(
                          color: MyColors.primary,
                        ),
                  ),
                ),
              ),
            ),
            const SizedBox(width: 8),
            Expanded(
              child: PrimaryButton(
                onPressed: () {},
                child: Center(
                  child: Text(
                    'Buy',
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
      body: SingleChildScrollView(
        child: Container(
          width: MediaQuery.of(context).size.width,
          margin: const EdgeInsets.all(24),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                'Shipping Address',
                style: Theme.of(context).textTheme.headlineMedium,
              ),
              const SizedBox(height: 16),
              Container(
                padding: const EdgeInsets.all(16),
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(8),
                  boxShadow: BoxShadowDecorator().defaultShadow(context),
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'John Doe',
                      style: Theme.of(context).textTheme.labelMedium?.copyWith(
                            color: Colors.grey,
                          ),
                    ),
                    const SizedBox(height: 8),
                    Text(
                      'Jl. Margonda Raya No. 57, Beji, Kec. Beji, Kota Depok, Jawa Barat 16424',
                      style: Theme.of(context).textTheme.titleSmall?.copyWith(
                            color: Colors.black,
                          ),
                    ),
                    const SizedBox(height: 8),
                    Text(
                      '081234567890',
                      style: Theme.of(context).textTheme.labelMedium?.copyWith(
                            color: Colors.black,
                          ),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 16),
              Text(
                'Order Summary',
                style: Theme.of(context).textTheme.headlineMedium,
              ),
              const SizedBox(height: 16),
              Container(
                padding: const EdgeInsets.all(16),
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(8),
                  boxShadow: BoxShadowDecorator().defaultShadow(context),
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          'Toko Apap',
                          style:
                              Theme.of(context).textTheme.labelMedium?.copyWith(
                                    color: Colors.grey,
                                  ),
                        ),
                        Text(
                          '12/12/2021',
                          style:
                              Theme.of(context).textTheme.labelMedium?.copyWith(
                                    color: Colors.grey,
                                  ),
                        ),
                      ],
                    ),
                    const SizedBox(height: 8),
                    Row(
                      children: [
                        Expanded(
                          child: Text(
                            'Xiaomi Redmi Note 10 Pro 8GB 128GB - Glacier Blue',
                            style: Theme.of(context)
                                .textTheme
                                .titleSmall
                                ?.copyWith(
                                  color: Colors.black,
                                ),
                          ),
                        ),
                        Text(
                          'Rp 100.000',
                          style:
                              Theme.of(context).textTheme.labelMedium?.copyWith(
                                    color: Colors.black,
                                  ),
                        ),
                      ],
                    ),
                    const SizedBox(height: 8),
                    Row(
                      children: [
                        Expanded(
                          child: Text(
                            'Xiaomi Redmi Note 10 Pro 8GB 128GB - Glacier Blue',
                            style: Theme.of(context)
                                .textTheme
                                .titleSmall
                                ?.copyWith(
                                  color: Colors.black,
                                ),
                          ),
                        ),
                        Text(
                          'Rp 100.000',
                          style:
                              Theme.of(context).textTheme.labelMedium?.copyWith(
                                    color: Colors.black,
                                  ),
                        ),
                      ],
                    ),
                    const SizedBox(height: 8),
                    Row(
                      children: [
                        Expanded(
                          child: Text(
                            'Xiaomi Redmi Note 10 Pro 8GB 128GB - Glacier Blue',
                            style: Theme.of(context)
                                .textTheme
                                .titleSmall
                                ?.copyWith(
                                  color: Colors.black,
                                ),
                          ),
                        ),
                        Text(
                          'Rp 100.000',
                          style:
                              Theme.of(context).textTheme.labelMedium?.copyWith(
                                    color: Colors.black,
                                  ),
                        ),
                      ],
                    ),
                    const SizedBox(height: 8),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          'Total',
                          style:
                              Theme.of(context).textTheme.labelMedium?.copyWith(
                                    color: Colors.grey,
                                  ),
                        ),
                        Text(
                          'Rp 300.000',
                          style:
                              Theme.of(context).textTheme.labelMedium?.copyWith(
                                    color: Colors.black,
                                  ),
                        ),
                      ],
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
