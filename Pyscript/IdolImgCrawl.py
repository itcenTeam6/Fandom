from selenium import webdriver as wb
from selenium.webdriver import ActionChains
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from tqdm import tqdm
from random import sample
import mariadb
import json, time, os

class IdolImgCrawlingApp:
    def __init__(self) -> None:
        self.__options = wb.ChromeOptions()
        self.__options.add_argument("--start-maximized")
        self.__options.add_argument("--disable-blink-features=AutomationControlled")
        self.__options.add_experimental_option("excludeSwitches", ["enable-automation", "enable-logging"])
        self.__options.add_experimental_option('useAutomationExtension', False)
        self.__service = Service(ChromeDriverManager(path='Driver').install())
        self.imgFolder = os.path.join(os.getcwd(), 'IdolImg')
        self.matchDict = {"아이브" : 1, "뉴진스" : 3, "블랙핑크" : 2, "르세라핌" : 4}

        if not os.path.exists(self.imgFolder):
            os.mkdir(self.imgFolder)
    
    def makeFolder(self, idolDict):
        for group in idolDict:
            groupPath = os.path.join(self.imgFolder, group)
            if not os.path.exists(groupPath):
                os.mkdir(groupPath)
    
    def groupCrawling(self, groupName, groupLi):
        url = "https://search.naver.com/search.naver?where=image&sm=tab_jum"
        
        browser = wb.Chrome(service=self.__service, options=self.__options)
        browser.get(url)
        browser.delete_all_cookies()
        time.sleep(1)
        
        for name in tqdm(groupLi):
            # input name
            searchBox = browser.find_element(By.XPATH, '//*[@id="nx_query"]')
            ActionChains(browser).click(searchBox).double_click(searchBox).send_keys(Keys.DELETE).send_keys(name).send_keys(Keys.ENTER).perform()
        
            # pageDown
            for _ in range(8):
                browser.find_element(By.TAG_NAME, 'body').send_keys(Keys.PAGE_DOWN)
                time.sleep(0.7)
        
            # img save
            starImg = browser.find_elements(By.CSS_SELECTOR, 'div.photo_tile._grid  > div._item > div._photoBox > div.thumb > a > img')
            destData = {name : [ele.get_attribute('src') for ele in starImg[:60]]}
            
            with open(os.path.join(self.imgFolder, groupName, f'{name}.json'), 'w', encoding='utf-8') as f:
                json.dump(destData, f, ensure_ascii=False)
        
        browser.close()
        browser.quit()
        
    def dbConnection(self):
        connection = mariadb.connect(user="root", password="mariadb", host="127.0.0.1", port=3306, database="newjeans")
        cursor = connection.cursor()
        return connection, cursor
    
    def insertIdol(self):
        connection, cursor = self.dbConnection()
        
        try:
            sql = "insert into idol (idolid, idol_name, idol_main_img) values (%s, %s, %s)"
            insertData = [
                (1, "IVE", "https://pbs.twimg.com/media/FFgr7uBVIAAoYYx.jpg:large"),
                (2, "BlackPink", "https://4kwallpapers.com/images/wallpapers/blackpink-pubg-mobile-pink-background-2560x1440-3105.jpg"),
                (3, "NewJeans", "https://images.chosun.com/resizer/gst8Y76TWvb559SjLSh43AmEUrU=/2000x1333/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/GO7X3DGRXZDGAPFJJRGNZBR4VI.jpg"),
                (4, "LESSERAFIM", "https://dbkpop.com/wp-content/uploads/2022/09/le_sserafim_antifragile_teaser_frozen_aquamarine_all_group_2.jpg")
            ]
            
            cursor.executemany(sql, insertData)
            connection.commit()
            print("Insert Idol Done!!")
        except Exception as e:
            raise e
        
        cursor.close()
        connection.close()
        
    def insertIdolImg(self):
        connection, cursor = self.dbConnection()
        
        try:
            sql = """
                insert into idol_img (idol_name, img_date, img_path, ms_type, idolid)
                values (%s, NOW(), %s, %s, %s)
            """
            
            insertData = []
            for folder in os.listdir(self.imgFolder):
            
                innerFolder = os.path.join(self.imgFolder, folder)
                idolId = self.matchDict[folder]
                
                for idol in os.listdir(innerFolder):
                    
                    if idol.endswith(".json"):
                        idolName = idol.split('.')[0] 
                        
                        with open(os.path.join(self.imgFolder, folder, idol), 'r', encoding='utf-8') as f:
                            jsonData = json.load(f)
                            f.close()
                        
                        msTypeLi = sample(["yes"] * 40 + ["no"] * 20, k=60)
                        for idx, val in enumerate(jsonData[idolName]):
                            insertData.append((idolName, val, msTypeLi[idx], idolId))
            
            cursor.executemany(sql, insertData)
            connection.commit()
            print("Insert IdolImg Done!!")
        except Exception as e:
            raise e

if __name__ == "__main__":
    app = IdolImgCrawlingApp()
    
    idolDict = {
        "뉴진스" : ["뉴진스", "뉴진스 민지", "뉴진스 하니", "뉴진스 다니엘", "뉴진스 해린", "뉴진스 혜인"],
        "블랙핑크" : ["블랙핑크", "블랙핑크 지수", "블랙핑크 제니", "블랙핑크 로제", "블랙핑크 리사"],
        "르세라핌" : ["르세라핌", "르세라핌 사쿠라", "르세라핌 김채원", "르세라핌 허윤진", "르세라핌 카즈하", "르세라핌 홍은채"],
        "아이브" : ["아이브", "아이브 안유진", "아이브 가을", "아이브 레이", "아이브 장원영", "아이브 리즈", "아이브 이서"]
    } 
    
    app.makeFolder(idolDict)
    
    for idol in idolDict:
        app.groupCrawling(groupName=idol, groupLi=idolDict[idol])
    
    app.insertIdol()
    app.insertIdolImg()