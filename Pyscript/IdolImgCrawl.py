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
        self.matchDict = {"뉴진스" : 1, "블랙핑크" : 2, "에스파" : 3, "아이브" : 4, "르세라핌" : 5, "오마이걸" : 6, "트와이스" : 7, "있지" : 8}

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
            
            imgLi = []
            for ele in starImg:
                ActionChains(browser).move_to_element(ele).perform()
                time.sleep(0.3)
                ele.click()
                
                imgLi.append(browser.find_element(By.XPATH, '//*[@id="main_pack"]/section[2]/div/div[2]/div/div/div[1]/div[1]/div/div/div[1]/div[1]/img').get_attribute('src'))
                
            with open(os.path.join(self.imgFolder, groupName, f'{name}.json'), 'w', encoding='utf-8') as f:
                json.dump({name : imgLi}, f, ensure_ascii=False)
        
        browser.close()
        browser.quit()
        
    def dbConnection(self):
        connection = mariadb.connect(user="root", password="mariadb", host="127.0.0.1", port=3306, database="newjeans")
        cursor = connection.cursor()
        return connection, cursor
    
    def insertIdol(self):
        connection, cursor = self.dbConnection()
        
        try:
            sql = "insert into idol (idolid, idol_name, idol_main_img, idol_sub_img) values (%s, %s, %s, %s)"
            insertData = [
                (1, "NewJeans", 
                 "https://cdn.clien.net/web/api/file/F01/13174997/2fdb6acdb5ecb3.jpg",
                 "https://talkimg.imbc.com/TVianUpload/tvian/TViews/image/2022/08/08/dc0a7664-93fe-403e-8e12-f3066589a988.JPEG"),
                (2, "BlackPink", 
                 "https://4kwallpapers.com/images/wallpapers/blackpink-pubg-mobile-pink-background-2560x1440-3105.jpg",
                 "https://weverse-phinf.pstatic.net/MjAyMjA5MTZfMTU3/MDAxNjYzMzAwNTMwNDg0.oWitHeFDQwy5XciQ0h2bxqq14H-a7GqzdKSwc5RqMU0g.Qo3Np6u6Y3chZy_xIrGlwANsJpphdm-FVGGar_5aoeQg.PNG/45377809542097982318a06ef-10d1-48da-888d-b1d1c97f89ca.png?type=f706_740"),
                (3, "AESPA", 
                 "https://rare-gallery.com/uploads/posts/340441-Aespa-Kpop-K-pop-Girl-Group-Girls-Members-Karina-Ningning-Giselle-Winter.jpg",
                 "http://image.koreatimes.com/article/2022/06/21/20220621213004621.jpg"),
                (4, "IVE", 
                 "https://pbs.twimg.com/media/FFgr7uBVIAAoYYx.jpg:large",
                 "https://news.koreadaily.com/data/photo/2022/12/02/202212021643773262_6389aece7f553.jpg"),
                (5, "LESSERAFIM",
                 "https://dbkpop.com/wp-content/uploads/2022/09/le_sserafim_antifragile_teaser_frozen_aquamarine_all_group_2.jpg",
                 "https://cdn.gukjenews.com/news/photo/202212/2624316_2636644_3446.jpg"),
                (6, "OHMYGIRL",
                 "https://image.ytn.co.kr/general/jpg/2022/0331/202203310920184109_d.jpg",
                 "https://image.winudf.com/v2/image1/Y29tLmhhZWRjdW93dy5teXdhbGxwYXBlci5PaG15Z2lybF9zY3JlZW5fN18xNjE0MTI4NDcyXzA3Mg/screen-7.jpg?fakeurl=1&type=.jpg"),
                (7, "TWICE",
                 "https://rare-gallery.com/mocahbig/394887-twice-k-pop-members-alcohol-free-4k-pc-wallpaper.jpg",
                 "https://w0.peakpx.com/wallpaper/239/739/HD-wallpaper-twice-chaeryeong-dahyun-jeongyeon-jihyo-mina-momo-nayeon-sana-twice-tzuyu.jpg"),
                (8, "ITZY",
                 "https://rare-gallery.com/uploads/posts/346263-ITZY-Kpop-K-pop-Girls-Members.jpg",
                 "http://img.sportsworldi.com/content/image/2020/08/11/20200811513135.jpg")
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
        "뉴진스" : ["뉴진스"],
        "블랙핑크" : ["블랙핑크"],
        "에스파" : ["에스파"],
        "아이브" : ["아이브"],
        "르세라핌" : ["르세라핌"],
        "오마이걸" : ["오마이걸"],
        "트와이스" : ["트와이스"],
        "있지" : ["있지"]
    }
    
    app.makeFolder(idolDict)
    
    # for idol in idolDict:
    #     app.groupCrawling(groupName=idol, groupLi=idolDict[idol])
    
    app.insertIdol()
    app.insertIdolImg()