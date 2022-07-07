import os
import fnmatch
import sys
import time, fire, subprocess

basepath = os.path.dirname(os.path.realpath(__file__))

# for file in os.listdir('/etc/init.d/'):
#     if fnmatch.fnmatch(file, 'tomcat*'):
#         tomcatN = file

# os.system('cp ' + path + '/WEB-INF/lib/mysql-connector-java-5.1.5-bin.jar /usr/share/' + tomcatN + '/lib')
# os.system('java -Xmx1024m -Dfile.encoding=utf-8 -classpath /usr/share/' + tomcatN + '/lib/servlet-api.jar:' + path + '/WEB-INF/lib/jericho-html-3.2.jar:' + path + '/WEB-INF/lib/jackson-all-1.8.3.jar:' + path + '/WEB-INF/lib/jdom.jar:' + path + '/WEB-INF/lib/mysql-connector-java-5.1.5-bin.jar:' + path + '/WEB-INF/classes Setup ' + path)

# os.system('sudo /etc/init.d/' + tomcatN + ' stop')
# os.system('sudo /etc/init.d/mysql restart')
# os.system('sudo /etc/init.d/' + tomcatN + ' start')



class TeacherExamSetup(object):
    ''' TeacherExam Setup
    搭配參數如下：
    install: 直接安裝並進行必要設定
    '''

    def __init__(self, offset=1):
        self._offset = offset

    def _exec2(self, cmd):
        print(basepath+"/Setup.py EXEC2= " + cmd, flush=True)
        os.system(cmd)

    def _exec(self, cmd):
        print(basepath+"/Setup.py EXEC= " + cmd)
        try:
            completed = subprocess.run(
                cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
            # print('completed:', completed)
        except subprocess.CalledProcessError as err:
            print('CalledERROR:', err)
            return []
        else:
            print('returncode:', completed.returncode)
            stdout = completed.stdout.decode('utf-8').strip()
            print(f'STDOUT({len(stdout)} bytes): \n'+stdout)
            stderr = completed.stderr.decode('utf-8').strip()
            print(f'STDERR({len(stderr)} bytes): \n'+stderr)
            if stdout=='':
                return []
            else:
                return stdout.split('\n')

    def _pull_tags(self, tmpdir):
        cmd = 'git --git-dir='+tmpdir+'/.git --work-tree='+tmpdir+' tag '
        print("cmd=" + cmd)
        tags = subprocess.check_output(cmd.split()).decode(
            'utf-8').rstrip().split('\n')
        count = 0
        for tag in tags:
            print(str(count) + ". tag=" + tag)
            count = count + 1
        tagindex = input("請問要取出哪一個 tag 版本？ ")
        self._exec(
            'git --git-dir='+tmpdir+'/.git --work-tree='+tmpdir+' checkout '+tags[int(tagindex)])
        open(tmpdir + '/WebContent/META-INF/Version.txt',
             mode='w', encoding='utf-8').write(tags[int(tagindex)])

    def _pull_latestversion(self, tmpdir):
        '''
        取得最新的 tag
        git describe --abbrev=0 --tags
        '''
        cmd = f'git --git-dir={tmpdir}/.git --work-tree={tmpdir} tag '
        taglines = self._exec(cmd)
        print('type(taglines)=', type(taglines), taglines)

        latest_tag = []
        for tagline in taglines:
            tag = [int(x) for x in tagline.split('.')]
            if len(latest_tag) == 0 or latest_tag < tag:
                latest_tag = tag

        latest_tag_line = '.'.join(str(x) for x in latest_tag)
        # cmd = 'git --git-dir=' + tmpdir + '/.git --work-tree=' + \
        #     tmpdir + ' describe --abbrev=0 --tags'
        # latest_tag = self._exec(cmd)

        self._exec(
            f'git --git-dir={tmpdir}/.git --work-tree={tmpdir} checkout -b branch_{latest_tag_line} {latest_tag_line}')
        open(tmpdir + '/WebContent/META-INF/Version.txt',
             mode='w', encoding='utf-8').write(latest_tag_line)

    def install(self, dbpass, dbuser='root', githost='github.com', warname='TeacherExam', appname='TeacherExam', version="latestversion", clean=True, SSL=False, tomcatN='tomcat?', pythonN='python3'):
        # self._exec('apt-get update')
        # self._exec('apt-get upgrade')
        # self._exec('apt install rsync python3 python3-pip git mysql-server default-jdk tomcat9 ant')
        # self._exec('pip3 install --upgrade pip')
        # self._exec('pip3 install beautifulsoup4 lxml fire')
        # self._exec('')
        # self._exec('')
        # self._exec('')
        # self._exec('')
        # self._exec('')
        # self._exec('')
        #sudo apt-get update
        # sudo apt-get upgrade
        # sudo apt install rsync python3 python3-pip git mysql-server default-jdk tomcat9 ant
# sudo pip3 install --upgrade pip
# sudo pip3 install beautifulsoup4 lxml fire
# mysql> ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '!@34TeacherExam';
# mysql> CREATE USER 'teacherexam'@'localhost' IDENTIFIED BY '!@34TeacherExam';
# # 這一行必須加，teacherexam 必須設定好 native_password 才行
# mysql> ALTER USER 'teacherexam'@'localhost' IDENTIFIED WITH mysql_native_password BY '!@34TeacherExam';

# mysql> GRANT ALL PRIVILEGES ON `teacherexam` . * TO 'teacherexam'@'localhost';

# sudo apt-get install authbind

# sudo touch /etc/authbind/byport/80
# sudo chown tomcat: /etc/authbind/byport/80
# sudo chmod 500 /etc/authbind/byport/80

# sudo systemctl restart mysql
# sudo systemctl restart tomcat9


        gitpath = appname + "_gitclone"
        # 更新 v2
        if tomcatN == 'tomcat?':
            for file in os.listdir('/etc/'):
                if 'tomcat' in file:
                    tomcatN = file



        # # 從遠端 pull 回來
        gituri = f"https://{githost}/jiangsir/{appname}"

        self._exec('rm -rf '+gitpath)
        result = os.system(
            f'git clone {gituri}.git/ {gitpath} --branch master')
        if result == 0:
            self._exec('rm -rf '+basepath)
            base = basepath[:basepath.rfind('/')]
            self._exec(f'mv {base}/{gitpath} {base}/{appname}')
            self._exec2(f"sudo {pythonN} {basepath}/Setup.py upgrade --dbuser '{dbuser}' --dbpass '{dbpass}' \
                    --warname '{warname}' --appname '{appname}' --githost '{githost}' --version '{version}' --clean {clean} --SSL {SSL} --tomcatN '{tomcatN}' --pythonN '{pythonN}'")
        else:
            print('執行 git clone 失敗！')

    def upgrade(self, dbpass, warname="TeacherExam", appname="TeacherExam", dbuser='root', githost='github.com', version="latestversion", clean=True, SSL=False, tomcatN='tomcat?', pythonN='python3'):
        ''' 安裝/設定 TeacherExam 系統 
        '''
        # appname = input("請輸入 git host 上的應用程式名稱: ")  # ex: ZeroJudge

        dbname = appname.lower()

        apptmpdir = os.path.join("/tmp", appname)

        if tomcatN == 'tomcat?':
            for file in os.listdir('/etc/'):
                if 'tomcat' in file:
                    tomcatN = file

        self._exec('rm -rf ' + apptmpdir)
        self._exec('mkdir ' + apptmpdir)
        gituri = f"https://{githost}/jiangsir/{appname}.git {apptmpdir}"
        os.system(f'git clone {gituri}')

        #choose4 = input("["+appname+"] 請問要取出 1.tag 或者 2. branch：(1, 2) ")
        if version == "latestversion":
            '''
            自動取出最新版
            '''
            self._pull_latestversion(apptmpdir)

        elif version == "tag":
            self._pull_tags(apptmpdir)

        elif version == "branch":
            cmd = f'git --git-dir={apptmpdir}/.git --work-tree={apptmpdir} branch -a --sort=-committerdate'
            print("cmd=" + cmd)
            branchs = subprocess.check_output(
                cmd.split()).decode('utf-8').rstrip().split('\n')
            count = 0
            for branch in branchs:
                print(str(count) + ". " + branch)
                count = count + 1
            index = int(input("請問要取出哪一個 branch？ "))
            # branchname = branchs[int(index)].split('/')[-1]
            branchname = branchs[index].replace('remotes/origin/', '')
            self._exec(
                f'git --git-dir={apptmpdir}/.git --work-tree={apptmpdir} checkout {branchname}')
            cmd = f'git --git-dir={apptmpdir}/.git --work-tree={apptmpdir} show-branch -g'
            message = subprocess.check_output(cmd.split()).decode('utf-8')
            print('message= ' + message)
            open(apptmpdir + '/WebContent/META-INF/Version.txt',
                 mode='w', encoding='utf-8').write(message)

        else:
            print('version 的參數只能為 ("latestversion", "branch", "tag") ')
            sys.exit()

        # 清除所有的 BOM
        for root, dirs, files in os.walk(apptmpdir + "/src/"):
            for file in files:
                if file.endswith(".java"):
                    # print(os.path.join(root, file))
                    s = open(os.path.join(root, file), mode='r',
                             encoding='utf-8-sig').read()
                    open(os.path.join(root, file), mode='w',
                         encoding='utf-8').write(s)

        # if warname == None:
        #     warname = 'ROOT'
        # else:
        #     warname = input(
        #         "開始打包 war, 請輸入 所要使用的 App Name 。(不輸入則預設為 ROOT.war): ")
        #     if warname == '':
        #         warname = 'ROOT'

        if clean == True:
            target = 'clean makewar callpy'
        else:
            target = 'makewar callpy'

        self._exec(
            f'ant -f {apptmpdir}/build.xml {target} -Dappname={warname} -DTOMCAT_HOME=/usr/share/{tomcatN}/')

        # while int(subprocess.call(f"mysql -u {dbuser} -p'{dbpass}' -e \"USE {dbname};\"", shell=True)) != 0:
        #     dbpass = input("輸入資料庫 "+dbuser+" 密碼：")

        self._exec2(
            f"{pythonN} {apptmpdir}/build.py build --warname '{warname}' --dbuser '{dbuser}' --dbpass '{dbpass}' --dbname '{dbname}' --SSL {SSL} --tomcatN '{tomcatN}' ")


if __name__ == '__main__':
    fire.Fire(TeacherExamSetup)
