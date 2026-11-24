#include <bits/stdc++.h>
using namespace std;
 
 class FoodItem{
    public:
        string name;
        double price;

        FoodItem(string name,double price){
            this->name=name;
            this->price=price;
        }
 };

 class Order{
    private:
        vector<FoodItem> items;
        bool placed=false;
    public:
        void addItem(string name,double price){
            if(placed){
                cout<<"Order already placed!"<<endl;
                return;
            }
            items.emplace_back(name,price);
            cout<<"Added Item!"<<endl;
        }
        double calculateBill(){
            double total=0;
            for(auto &item:items){
                total+=item.price;
            }
            return total;
        }
        void placeOrder(){
            if(placed){
                cout<<"Order already placed!"<<endl;
                return;
            }
            if(items.empty()){
                cout<<"Nothing into the cart!"<<endl;
                return;
            }
            placed=true;
            cout<<"Order Placed Successfully!"<<endl;
            displayOrder();
        }
        void displayOrder() {
            cout << "\nOrder Details:\n";

            for (auto &item : items) {
                cout << item.name
                    << " - Rs. " << item.price << endl;
            }

            cout << "Total Bill: Rs. "
                << calculateBill() << endl;
        }
 };
 int main(){
    Order order;
    order.addItem("Pizza",250);
    order.addItem("Burger",120);
    order.addItem("Cold Drink",100);
    cout<<"\nTotal Amount: Rs. "<<order.calculateBill()<<endl;
    order.placeOrder();
 }