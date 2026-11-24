/*
Deposit money.
Withdraw money.
Check account balance.
Prevent withdrawals when the balance is insufficient.
*/

#include <bits/stdc++.h>
using namespace std;
class BankAccount{
    private:
        int accountNumber;
        double balance;
    public:
        BankAccount(int accountNumber,double balance){
            this->accountNumber=accountNumber;
            this->balance=balance;
        }
        void deposit(double amount){
            if(amount>0){
                cout<<"Deposit "<<amount<<endl;
                balance+=amount;
                cout<<"New Balance After Deposit "<<balance<<endl;
                return;
            }else{
                cout<<"Invalid amount."<<endl;
            }
        }
        void withdraw(double amount){
            if(amount<=0){
                cout<<"Invalid amount."<<endl;
                return;
            }
            cout<<"Withdraw "<<amount<<endl;
            cout<<"Updated balance "<<balance<<endl;
        }
        void checkBalance(){
            cout<<"Account Number: "<<accountNumber<<endl;
            cout<<"Balance: "<<balance<<endl;
        }
};

int main(){
    BankAccount account(100,5000);
    account.checkBalance();

    account.deposit(2000);
    account.withdraw(1500);

    account.checkBalance();

    account.withdraw(10000);

    return 0;
}