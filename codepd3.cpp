#include <iostream>
#include <unordered_map>
#include <vector>
#include <ctime>
#include <iomanip>

using namespace std;

// Function to calculate working hours between two timestamps
double calculateWorkingHours(time_t start_time, time_t end_time) {
    return difftime(end_time, start_time) / 3600.0; // Converting seconds to hours
}

int main() {
    unordered_map<string, unordered_map<string, double>> month_employee_stats;

    // Loop through the time logs for each employee
    while (true) {
        string empid, timestamp, status;
        cin >> empid;
        if (empid == "END") break;
        cin >> timestamp >> status;

        // Extract year and month from the timestamp
        string month = timestamp.substr(0, 7);

        // Convert timestamp to time_t format
        struct tm timeinfo = {0};
        strptime(timestamp.c_str(), "%Y-%m-%d %H:%M:%S", &timeinfo);
        time_t log_time = mktime(&timeinfo);

        if (status == "clock-in") {
            // Store the current time log as the last clock-in time for the employee for that day
            month_employee_stats[month][empid+"_last_clockin"] = log_time;
        } else if (status == "clock-out") {
            // Calculate working hours for the employee for that day
            double working_hours = calculateWorkingHours(month_employee_stats[month][empid+"_last_clockin"], log_time);

            // If no clock-out time log is found, regularize the attendance by considering the earlier time out of the office clock-out time and standard 6 hours of shift for an employee.
            if (working_hours < 0) {
                time_t office_closing_time = mktime(&timeinfo);
                office_closing_time += (19-8)*3600; // Assuming office closing time is 7:30 PM
                double regularized_hours = calculateWorkingHours(month_employee_stats[month][empid+"_last_clockin"], office_closing_time);
                regularized_hours = max(regularized_hours, 6.0); // Assuming minimum work hour is 6 hours
                working_hours = regularized_hours;
            }

            // Add the working hours to the total working hours for the employee for that month
            month_employee_stats[month][empid+"_working_hours"] += working_hours;

            // Increment the number of working days for the employee for that month
            month_employee_stats[month][empid+"_working_days"] += 1;

            // If month not in month_employee_stats, create a new entry with employee's working hours, number of working days, and employee ID as both the best and worst employees for that month.
            if (month_employee_stats[month].empty()) {
                month_employee_stats[month][empid+"_working_hours"] = working_hours;
                month_employee_stats[month][empid+"_working_days"] = 1;
                month_employee_stats[month]["best_employee"] = empid;
                month_employee_stats[month]["worst_employee"] = empid;
            } else {
                // If employee's working hours for the month is higher than the best employee's working hours for that month, update the best employee for that month.
                if (month_employee_stats[month][empid+"_working_hours"] > month_employee_stats[month][month_employee_stats[month]["best_employee"]+"_working_hours"]) {
                    month_employee_stats[month]["best_employee"] = empid;
                }

                // If employee's
