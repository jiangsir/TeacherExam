
# 教師甄選報名繳費系統

VirtualBox 虛擬機請使用 6.0 以上版本:  
[TeacherExam2 VM下載](https://drive.google.com/file/d/1rkkmFmkXQeaUaUpHQXkSDksO9NkzP4OQ/view?usp=sharing)

    進入WEB 頁面:
    http://[Your IP or Domain]/TeacherExam

    
    預設系統資訊：
    OS: Ubuntu Desktop 20.04
    OS 預設帳密: zero / !@34TeacherExam
    DB 預設帳密: teacherexam / !@34TeacherExam
    WEB 預設帳密: admin / !@34TeacherExam

    更改 OS 密碼:
    passwd
    更改 DB 密碼:
    mysqladmin -u root -p password
    * 更改完後應再次執行「一行升級」以便更新資料庫相關設定。    
    更改 WEB 密碼:
    打開 127.0.0.1/TeacherExam -> 右下角【管理】->登入管理員->管理員帳號->更改密碼


# 升級指令:

執行以下指令將會回到 github 抓取最新版本並安裝。

    sudo python3 ~/TeacherExam/Setup.py install --dbuser 'teacherexam' --dbpass '!@34TeacherExam'