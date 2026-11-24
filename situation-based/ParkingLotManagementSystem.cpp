#include <bits/stdc++.h>
using namespace std;

/*
Vehicles can enter and occupy an available slot.
Vehicles can leave the parking lot.
The system should display available slots.
A vehicle cannot be parked twice.
*/

class ParkingLot{
    private:
        vector<string> slots;
    public:
        ParkingLot(int capacity){
            slots.resize(capacity,"");
        }
        void ParkingVehicle(string vehicleNumber){
            for(string str:slots){
                if(str==vehicleNumber){
                    cout<<"Vehicle already parked"<<endl;
                    return;
                }
            }
            for(int i=0;i<slots.size();i++){
                if(slots[i]==""){
                    slots[i]=vehicleNumber;
                    cout<<"Vehicle "<<vehicleNumber<< " Parked at "<<i+1<<" slot."<<endl;
                    return;
                }
            }
            cout<<"Sorry Parking Lot is full.\n";
        }
        void removeVehicle(string vehicleNumber){
            for(int i=0;i<slots.size();i++){
                if(slots[i]==vehicleNumber){
                    slots[i]="";
                    cout<<"Vehicle "<<vehicleNumber<<" Has been removed."<<endl;
                    return;
                }
            }
            cout<<"Vehicle not found."<<endl;
        }
        void displayAvailableSlots(){
            cout<<"Available slots: ";
            for(int i=0;i<slots.size();i++){
                if(slots[i]==""){
                    cout<<i+1<<" ";
                }
            }
            cout<<endl;
        }
};
int main() {
    ParkingLot lot(3);

    lot.ParkingVehicle("GJ01AB1234");
    lot.ParkingVehicle("GJ01CD5678");

    lot.displayAvailableSlots();

    lot.removeVehicle("GJ01AB1234");

    lot.displayAvailableSlots();

    lot.ParkingVehicle("GJ01EF9999");

    lot.ParkingVehicle("GJ01XY1111");
    lot.ParkingVehicle("GJ02MB4321");

    return 0;
}