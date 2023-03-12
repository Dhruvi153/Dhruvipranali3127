from collections import defaultdict
from datetime import datetime, timedelta

def identify_employee_stats(T):
    attendance = defaultdict(lambda: defaultdict(lambda: {'working_hours': 0, 'attendance_days': 0}))
    
    for entry in T:
        emp_id, timestamp, status = entry
        
        month = datetime.strptime(timestamp, '%Y-%m-%d %H:%M:%S').strftime('%Y-%m')
        
        if emp_id not in attendance[month]:
            attendance[month][emp_id] = {'working_hours': 0, 'attendance_days': 0}
        
        if status == 'clock-in':
            attendance[month][emp_id]['in-time'] = datetime.strptime(timestamp, '%Y-%m-%d %H:%M:%S')
        elif status == 'clock-out':
            out_time = datetime.strptime(timestamp, '%Y-%m-%d %H:%M:%S')
            if 'in-time' in attendance[month][emp_id]:
                in_time = attendance[month][emp_id]['in-time']
                working_hours = (out_time - in_time).total_seconds() / 3600.0
                if working_hours < 6.0:
                    working_hours = 6.0
                elif out_time.hour >= 19:
                    working_hours = (datetime.strptime(month + '-01 19:30:00', '%Y-%m-%d %H:%M:%S') - in_time).total_seconds() / 3600.0
                attendance[month][emp_id]['working_hours'] += working_hours
                attendance[month][emp_id]['attendance_days'] += 1
    
    for month in attendance:
        total_working_hours = [attendance[month][emp_id]['working_hours'] for emp_id in attendance[month]]
        avg_working_hours = sum(total_working_hours) / len(total_working_hours)
        best_employee = max(attendance[month], key=lambda emp_id: attendance[month][emp_id]['working_hours'])
        worst_employee = min(attendance[month], key=lambda emp_id
