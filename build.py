import os
import fnmatch
import time
import subprocess
import datetime
import sys
import fire
import re
from bs4 import BeautifulSoup

# for file in os.listdir('/etc/init.d/'):
#     if fnmatch.fnmatch(file, 'tomcat*'):
#         tomcatN = file
# webappspath = '/var/lib/' + tomcatN + '/webapps/'

basedir = os.path.split(os.path.realpath(__file__))[0]


class TeacherExamBuild(object):
    ''' TeacherExam Build
    '''
    ##########################################################################

    def currentVersion(self, webappspath):
        ''' 
        讀取 Version.txt 內的版本編號
        '''
        with open(webappspath + '/WebContent/META-INF/Version.txt', mode='r', encoding='utf-8') as versionfile:
            return versionfile.readline()

    def searchFiles(self, basepath, pattern):
        ''' 
        pattern 是以 正規表達式表示
        '''
        # [0-9]+.*\.jpg : 以數字開頭的 .jpg 檔
        #
        files = [f for f in os.listdir(basepath) if re.match(pattern, f)]
        return sorted(files, reverse=True)

    def os_exec(self, cmd):
        try:
            completed = subprocess.run(
                cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
            # print('completed:', completed)
        except subprocess.CalledProcessError as err:
            print('CalledERROR:', err)
            return []
        else:
            print(f'returnCODE: {completed.returncode}, cmd:', cmd)
            stdout = completed.stdout.decode('utf-8').strip()
            print(f'STDOUT: {stdout}')
            stderr = completed.stderr.decode('utf-8').strip()
            print(f'STDERR: {stderr}')
            return completed.returncode

    # def exec_returncode(self, cmd):
    #     '''
    #     回傳 exitcode, 以及訊息 list
    #     '''
    #     print('exec_returncode=' + cmd)
    #     exitcode, output = subprocess.getstatusoutput(cmd)
    #     outputs = output.split('\n')
    #     print('outputs: ', outputs)
    #     return exitcode, outputs

    def importSQL(self, dbname, dbuser):
        print("sqlfiles 列表：")
        #sqllist = []
        # for sqlfile in os.listdir(basedir):
        #     if fnmatch.fnmatch(sqlfile, '*.sql'):
        #         sqllist.append(sqlfile)
        sqlfiles = self.searchFiles(basedir, '.*\.sql$')

        for index, sqlfile in enumerate(sqlfiles):
            print(index, ". " + sqlfile)
        print(str(len(sqlfiles)) + ". exit")

        index = input("請選擇要匯入的資料庫檔案 *.sql？ ")
        if index == str(len(sqlfiles)):
            return False

        # dbname = input("準備匯入 " + sqllist[int(index)] + ", 請輸入資料庫名稱：")
        # cmd = 'cat ' + apptmpdir + '/' + sqlfile + ' | mysql -u root -p'
        # cmd = 'mysql -u root -p ' + dbname + ' < ' + apptmpdir + '/' + sqlfile
        # print(cmd)
        # os.system(cmd)

        print("匯入資料表到此資料庫: ")
        exec("mysql -u" + dbuser + " -p " +
             dbname + " < " + basedir + "/" + sqlfile)

        return True

    def backupSQL(self, dbname, dbuser, dbpass):
        ''' 
        備份現有資料庫
        '''
        backupfile = "Backup_"+dbname + "_" + \
            datetime.datetime.now().strftime('%Y%m%d-%H%M%S') + ".sql"

        backfiles = self.searchFiles('~/', '^Backup_'+dbname+"_.*\.sql&")
        if len(backfiles) >= 3:
            os.remove(backfiles[-1])

        print("進行資料表備份：匯出原資料庫 到 '" + backupfile + "'： ")
        self.os_exec("mysqldump -hlocalhost -u"+dbuser+" -p'" + dbpass +
                     "'  " + dbname + "  > ~/" + backupfile)
        ## self.os_exec(f'mysqldump -hlocalhost -u{dbuser} -p\'{dbpass}\'  {dbname}  > ~/{backupfile}')
        # 備份指令：mysqldump -uroot -p'!@34ZeroJudge' zerojudge > /home/zero/zerojudge-`date +%F-%H-%M`.sql

    ##########################################################################
    ##########################################################################

    ''' while True:
        choose5 = input("準備建立資料庫，請注意，若選擇建立資料庫，會現有資料庫備份後，建立新資料庫。請問是否重建資料庫？(Y/n) ")
        if choose5 == 'Y':
            #dbname = appname.lower()
            dbname = 'zerojudge' # 已經在 build.py 部署，直接指定 dbname 了
            print("即將「備份」現存資料庫，是否有已存在的資料庫(" + dbname + ")")
            print("Y. 若已存在則備份後刪除。")
            print("n. 若新安裝的系統尚未有資料庫，則直接建立資料庫。")
            choose6 = input("是否存在 (" + dbname + ")？(Y/n) ")
            if choose6 == 'Y':
                # 如果有現存的資料庫，就備份下來。
                dbname_backup = dbname + "_" + datetime.datetime.now().strftime('%Y%m%d-%H%M%S')
                print("匯出原資料庫： ")
                os_exec("mysqldump -hlocalhost -uroot -p  " + dbname + "  > " + dbname_backup + ".sql")
                print("建立一個備份資料庫: ")
                os_exec("mysqladmin -u root -p create " + dbname_backup)
                print("匯入資料到此資料庫: ")
                os_exec("mysql -hlocalhost -uroot -p " +
                    dbname_backup + " < " + dbname_backup + ".sql")
                print("刪除原資料庫： ")
                os_exec("mysqladmin -u root -p drop " + dbname)

            # 開始刪除原資料庫
            print("建立一個資料庫: ")
            os_exec("mysqladmin -u root -p create " + dbname)

            while True:
                if not importSQL(dbname):
                    break
            break
        elif choose5 == 'n':
            print("放棄重建資料庫。")
            break
    '''

    def build(self, warname, dbpass, dbname="teacherexam", dbuser="root", SSL=False, tomcatN='tomcat?'):
        ## self.os_exec('sudo /etc/init.d/' + tomcatN + ' restart')
        webappspath = '/var/lib/' + tomcatN + '/webapps/'

        self.os_exec(f'sudo rm -rf /var/lib/{tomcatN}/webapps/{warname}/')
        self.os_exec(
            f'sudo cp {basedir}/{warname}.war {webappspath}{warname}.war')
        contextpath = webappspath + warname + '/META-INF/context.xml'
        #contextpath = os.path.join(webappspath, warname, '/META-INF/context.xml')
        print("Waiting", end="", flush=True)
        while not os.path.isfile(contextpath):
            print(".", end="", flush=True)
            time.sleep(1)
        time.sleep(2)
        print()

        self.os_exec(
            f'sudo cp {webappspath}{warname}/WEB-INF/lib/mysql-connector-java-*.jar /usr/share/{tomcatN}/lib/')
        self.os_exec(
            f'sudo chmod +r /usr/share/{tomcatN}/lib/mysql-connector-java-*.jar')

        # check if war file 是否已經完全解開。
        #    break
        # if choose3 == 'n':
        #    sys.exit()

        # if dbpass == None:
        #    dbpass = input("輸入資料庫 root 密碼：")

        # returncode = -1
        # while int(returncode)!=0:
        #     dbpass = input("輸入資料庫 root 密碼：")
        #     returncode = subprocess.call("mysql -u root -p'"+dbpass+"' -e \"USE "+dbname+";\"", shell=True)

        # while int(subprocess.call("mysql -u "+dbuser+" -p'"+dbpass+"' -e \"USE "+dbname+";\"", shell=True)) != 0:
        #     # dbpass = input("輸入資料庫 "+dbuser+" 密碼：")
        #     pass

        print("IF NOT EXISTS 建立一個新資料庫(" + dbname + "): ")
        self.os_exec("export MYSQL_PWD='"+dbpass+"'; mysql -u "+dbuser+" -e \"CREATE DATABASE IF NOT EXISTS " + dbname + ";\"")

        DB_CREATEfiles = self.searchFiles(basedir, f'^{dbname}_CREATE\.sql')
        if len(DB_CREATEfiles) >= 1:
            print('發現 Sql 檔案: ', DB_CREATEfiles[0])
            self.os_exec("export MYSQL_PWD='"+dbpass+"'; mysql -u"+dbuser+" " +
                         dbname + " < " + basedir + "/" + DB_CREATEfiles[0])

        DB_ALTERfiles = self.searchFiles(
            basedir, f'^{dbname}_ALTER\.sql')
        if len(DB_ALTERfiles) >= 1:
            print('發現 DB_ALTER 檔案: ', DB_ALTERfiles[0])

            # 暫不進行備份
            # self.backupSQL(dbname, dbuser, dbpass)

            # print("資料庫(" + dbname + ") 進行資料表升級: ")

            # 不能整個 sql 檔匯入。
            # self.os_exec(
            #     f"mysql -u{dbuser} -p'{dbpass}' {dbname} < {basedir}/{sqlupdatefiles[0]}")

            # 必須要一行一行執行。
            with open(basedir + "/" + DB_ALTERfiles[0], 'r', encoding='UTF-8') as DB_ALTERfile:
                for ALTER_line in DB_ALTERfile:
                    ALTER_line = ALTER_line.strip()
                    ALTER_line = ALTER_line.replace('\"', '\'')
                    ALTER_line = ALTER_line.replace('`', '\\`')
                    if ALTER_line == '':
                        continue
                    self.os_exec(
                        f"export MYSQL_PWD='{dbpass}';mysql -u {dbuser} -e \"USE {dbname}; {ALTER_line}\"")

        print("開始設定資料庫到 context.xml")
        with open(contextpath, 'r+', encoding='UTF-8') as context:
            contextsoup = BeautifulSoup(context, 'xml')
            resource = contextsoup.find('Resource')
            #store = contextsoup.find('Store')

            resource['username'] = dbuser
            resource['password'] = dbpass
            #store['connectionName'] = dbuser
            #store['connectionPassword'] = dbpass

            self.os_exec('true > ' + contextpath)
            context.seek(0, 0)
            context.write(str(contextsoup))

        if SSL:
            self.os_exec('sudo cp ' + webappspath + warname +
                         '/WEB-INF/web_https.xml ' + webappspath + warname + '/WEB-INF/web.xml ')
        else:
            self.os_exec('sudo cp ' + webappspath + warname +
                         '/WEB-INF/web_http.xml ' + webappspath + warname + '/WEB-INF/web.xml ')

        #self.os_exec('/etc/init.d/' + tomcatN + ' restart')
        self.os_exec(f'sudo systemctl restart {tomcatN}')


if __name__ == '__main__':
    fire.Fire(TeacherExamBuild)
