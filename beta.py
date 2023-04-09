import traceback
import re
import sys
import os
import random
from urllib.request import urlopen
from bs4 import BeautifulSoup
from urllib.parse import quote
import urllib.request

# embed에 현재 시간 출력을 위해 import.
import datetime
import pytz

# 부산 버스 검색을 위해 import.
import requests
import json
import xmltodict

import numpy
import discord
from discord.ext import commands, tasks
import random

bot = commands.Bot(command_prefix='!', intents=discord.Intents.all())

# 봇 입장 시.


@bot.event
async def on_ready():
    print(f'Logged in as {bot.user} (ID: {bot.user.id})')
    game = discord.Game("딥러닝")
    await bot.change_presence(status=discord.Status.online, activity=game)


# help 명령어.
@bot.command()
async def 도움(ctx):
    embed = discord.Embed(title="도움말", color=0xffffff)
    embed.set_thumbnail(url="https://cdn.discordapp.com/attachments/1034757143611592757/1034757603798032444/1.png")
    embed.add_field(name="!도움", value="```베타가 학습한 것들을 알려드릴게요!```", inline=True)
    embed.add_field(name="!시트", value="```TRPG에서 사용하는 시트를 불러드릴게요!```", inline=True)
    embed.add_field(name="!ping", value="```pong을 출력.```", inline=True)
    embed.add_field(name="!임베드", value="```임베드 사용법을 출력.```", inline=True)
    embed.add_field(name="!안녕", value="```제가 인사해드릴게요!```", inline=True)
    embed.add_field(name="!roll", value="```1d100다이스를 굴려요. 당신의 SAN치는 어떨까요?```", inline=True)
    embed.add_field(name="이모티콘 출력", value="```이모티콘을 사용하면 확대해서 보여드릴게요!```", inline=True)
    embed.add_field(name="!로또 (숫자)", value="```입력한 (숫자)만큼의 로또 번호알려드려요!```", inline=True)
    embed.add_field(name="!메이플 (닉네임)", value="```메이플 월드의 유저를 찾아드려요!```", inline=True)
    embed.add_field(name="!날씨 (도시이름)", value="```입력한 도시의 현재 날씨를 검색해드려요!```", inline=True)
    embed.add_field(name="!부산버스 (버스 번호) (정류장 이름)", value="```부산의 원하는 (버스 번호)가 정차하는 (정류장 이름)이 있는지 찾아드려요!```", inline=True)
    embed.add_field(name="부산버스 명령어 이후 -> !정류장 (숫자)", value="```!부산버스 명령어로 찾은 정류장의 번호를 입력하면 버스가 언제 도착하는지 정보를 알려드려요!")
    await ctx.channel.send(embed=embed)


# TRPG 시트 불러오기.
@bot.command()
async def 시트(ctx):
    embed = discord.Embed(title="TRPG 캐릭터 모음 시트",
                          url="http://docs.google.com/spreadsheets/d/1TJdHVqnZQ1ST4XNqf9b7n_DY1Lidk0q5WjcuL5-usE8/edit?usp=drive_web&ouid=116231169099359786709",
                          description="TRPG 캐릭터 시트가 모인 시트를 불러입니다.\n(클릭시 이동함.)",
                          color=0xffffff)
    await ctx.channel.send(embed=embed)


# 임베드.
@bot.command()
async def 임베드(ctx):
    embed = discord.Embed(title="메인 제목", description="설명", color=0xfaf4cc)
    embed.set_footer(text="하단 설명")
    await ctx.channel.send(embed=embed)


# 핑퐁.
@bot.command()
async def ping(ctx):
    await ctx.channel.send('pong')


# hello.
@bot.command()
async def 안녕(ctx):
    embed = discord.Embed(color=0xffffff)
    embed.set_image(url="https://cdn.discordapp.com/attachments/1034757143611592757/1034769400777158687/2.png")
    await ctx.channel.send(embed=embed)


# 주사위 roll.
@bot.command()
async def roll(ctx):
    await ctx.send("주사위를 굴립니다.")
    a = random.randrange(1, 101)
    embed = discord.Embed(title="1d100의 결과는?", color=0xffffff)
    embed.add_field(name="결과", value=a)
    await ctx.channel.send(embed=embed)


# 이모티콘.
@bot.event
async def on_message(message):
    await bot.process_commands(message)

    if not message.guild or message.author.id == bot.user.id:
        return

    if (m := re.match(r"^<a?:[\w]+:([\d]+)>$", message.content)):
        if message.content.startswith("<a:"):
            ext = "gif"
        else:
            ext = "png"

        embed = discord.Embed(color=message.author.color)
        embed.set_author(name=message.author.display_name, icon_url=message.author.display_avatar)
        embed.set_image(url=f"https://cdn.discordapp.com/emojis/{m.group(1)}.{ext}")

        await bot.process_commands(message)
        await message.channel.send(embed=embed, reference=message.reference, mention_author=True)
        await message.delete()


# 로또 번호 추첨.
@bot.command()
async def 로또(ctx, count: int):
    embed = discord.Embed(title="로또 번호 추첨", timestamp=datetime.datetime.now(pytz.timezone('UTC')), color=0xfaf4cc)

    for i in range(count):
        lotto_num = []

        for j in range(6):
            lotto_num = numpy.random.choice(range(1, 46), 6, replace=False)

        lotto_num.sort()
        embed.add_field(name="결과", value=f'{i+1}. 로또번호 : {lotto_num}', inline=False)

    await ctx.channel.send(embed=embed)


# !rank 명령어 무시.
@bot.command()
async def rank(ctx):
    if ctx.author != bot.user:
        return


# 메이플 플레이어 정보. (웹스크롤링 이용.)
@bot.command()
async def 메이플(ctx, player):
    embed=discord.Embed(title="메이플 월드에 계시는 용사님이세요!", timestamp=datetime.datetime.now(pytz.timezone('UTC')))

    url = "https://maplestory.nexon.com/Ranking/World/Total?c="
    keyword = quote(player)
    search_url = url + keyword

    html = urlopen(search_url)

    soup = BeautifulSoup(html, "html.parser")

    info = soup.find("tr", {'class': 'search_com_chk'})

    try:
        # 플레이어 아바타 이미지.
        image = info.select_one("tr > td > span > img")['src']
        urllib.request.urlretrieve(f"{image}", "player_image.png")
        player_image = discord.File("player_image.png", filename = "image.png")
        embed.set_image(url = "attachment://image.png")

        # 플레이어 닉네임.
        nickname = info.find("a")
        embed.add_field(name="닉네임", value=f"{nickname.text}", inline=True)

        # 플레이어 직업.
        player_job = info.find("dd")
        embed.add_field(name="직업", value=f"{player_job.text}", inline=True)

        # 플레이어 레벨, 길드.
        tds = info.findAll("td")

        # 레벨. 
        embed.add_field(name="레벨", value=f"{tds[2].text}", inline=True)

        # 길드.
        if tds[5].text == True:
            embed.add_field(name="길드", value=f"{tds[5].text}", inline=True)
        else:
            embed.add_field(name="길드", value="없음", inline=True)

        await ctx.channel.send(embed=embed, file = player_image)
    except:
        await ctx.channel.send("메이플 월드에 없는 용사님이신거 같아요...")



# 현재 날씨 검색. (웹스크롤링 이용.)
@bot.command()
async def 날씨(ctx, location):
    embed=discord.Embed(title="찾으시는 지역의 날씨예요!", timestamp=datetime.datetime.now(pytz.timezone('UTC')))

    # 날씨 데이터.
    url = "https://www.weather.go.kr/w/obs-climate/land/city-obs.do"

    html = urlopen(url)

    soup = BeautifulSoup(html, "html.parser")

    info = soup.select("div > table > tbody > tr")

    # 시간 데이터.
    time_all = str(soup.select_one("div.cmp-table-topinfo"))

    date = time_all[53:-12]
    date = date.replace("기상실황표","")
    date = date.replace(".","-")

    time = time_all[64:-6]

    present_time = "기준 시간 : " + date + " " + time

    embed.set_author(name=present_time)

    # 날씨 데이터 출력.
    isFind = False # 리스트에서 값이 있는가 체크하는 변수.

    for li_info in info:
        search_location = li_info.find("a")

        if search_location.text == location:
            weather = li_info.select("td")

            embed.add_field(name="지역", value=f"{weather[0].text}", inline=True) # 지역.
            embed.add_field(name="현재 온도", value=f"{weather[5].text}  ℃", inline=True) # 현재 온도.
            embed.add_field(name="체감 온도", value=f"{weather[7].text}  ℃", inline=True) # 체감 온도.

            if weather[8].text.__len__() != 1: # 일 강수량(mm).
                embed.add_field(name="일 강수량(mm)", value=f"{weather[8].text} mm", inline=True)
            else:
                embed.add_field(name="일 강수량(mm)", value="0.0 mm", inline=True)

            if weather[9].text.__len__() != 1: # 적설량(cm).
                embed.add_field(name="적설량(cm)", value=f"{weather[9].text} cm", inline=True)
            else:
                embed.add_field(name="적설량(cm)", value="0.0 cm", inline=True)

            embed.add_field(name="습도(%)", value=f"{weather[10].text} %", inline=True) # 습도(%).
                
            isFind = True # 리스트안에서 찾음.

    if isFind == True: # 리스트 안에 있다면,
        await ctx.channel.send(embed=embed)
    else: # 리스트에서 없다면,
        await ctx.channel.send("제가 모르는 지역인 것 같아요...")

@날씨.error
async def 날씨_error(ctx, error):
    await ctx.send("지역을 입력하시지 않은 것 같아요...")



# 부산 버스 검색. (API 이용.)
@bot.command()
async def 부산버스(ctx, busan_busNo_input, busan_busStop_input):
    embed=discord.Embed(title="부산 버스 검색이에요!")
    embed.add_field(name="명령어 사용법 : !정류장 (정류장 번호)",value="입력한 (정류장 번호)의 실시간 도착 정보를 알려드릴게요!")

    # 1. 버스 번호 -> 노선 ID 구하기.
    url = 'http://apis.data.go.kr/6260000/BusanBIMS/busInfo'
    params = {'serviceKey' : 'cVAZvoe8Fo5X0jPOaI/iwkosf10ajo5M1M9smdhCik7esMTsHmgEnJnz+wNPRiQYj8uCB4TDtMsE9OBXu79gsA==',
            'lineno' : busan_busNo_input,
            }

    busan_busStop_response = requests.get(url, params=params)
    busan_busStop_contents = busan_busStop_response.text
    busan_busStop_jsonStr = json.dumps(xmltodict.parse(busan_busStop_contents))
    busan_busStop_dict = json.loads(busan_busStop_jsonStr)

    busan_bus = busan_busStop_dict['response']['body']['items']['item']
    row = {'a'}
    busan_list = [busan_bus, row]

    
    try:
        for busan_bus_i in busan_list:
            if busan_busNo_input == busan_bus_i['buslinenum']:
                busan_busId = busan_bus_i['lineid']
    except TypeError:
        try:
            for busan_bus_j in busan_busStop_dict['response']['body']['items']['item']:
                if busan_busNo_input == busan_bus_j['buslinenum']:
                    busan_busId = busan_bus_j['lineid']
        except:
            pass

    # 2. 노선 ID -> 원하는 정류소 ID 구하기.
    url = 'http://apis.data.go.kr/6260000/BusanBIMS/busInfoByRouteId'
    params = {'serviceKey' : 'cVAZvoe8Fo5X0jPOaI/iwkosf10ajo5M1M9smdhCik7esMTsHmgEnJnz+wNPRiQYj8uCB4TDtMsE9OBXu79gsA==',
            'lineid' : busan_busId,
            }
    busan_busId_response = requests.get(url, params=params)
    busan_busId_contents = busan_busId_response.text
    busan_busId_jsonStr = json.dumps(xmltodict.parse(busan_busId_contents))
    busan_busId_dict = json.loads(busan_busId_jsonStr)
    i = 1 # 다음 정류소 ID를 가져오기 위해 사용.
    busan_index = 1 # 딕셔너리 index.
    isOkay = False # 원하는 정류소이면 다음 정류소의 정보를 가져옴.
    isFind = False # 정류장을 찾은게 1개라도 있는지 체크.
    busan_bus_dict = dict() # 버스 정류소 딕셔너리.
    busan_bus_list = list() # bus_dict의 value에 들어갈 bus_list.
    
    for busan_busStop in busan_busId_dict['response']['body']['items']['item']:
        # (i - 1) 번째 뒤의 버스 정류소 정보를 찾아내기 위해 사용.
        if i == int(busan_busStop['bstopidx']) and isOkay == True:
            busan_bus_list.append(busan_busStop['bstopnm']) # dict의 value 값에 들어갈 list에 다음 정류소의 이름을 저장.
            busan_bus_dict[busan_index] = busan_bus_list
            
            embed.add_field(name=busan_index, value=f"{busan_bus_list[0]} | ({busan_bus_list[2]} 방면)", inline=False)
            busan_index += 1
            busan_bus_list = list()
            isOkay = False
        
        # 입력한 버스 정류소가 있으면,
        if busan_busStop_input in busan_busStop['bstopnm']:
            busan_bus_list.append(busan_busStop['bstopnm']) # dict의 value 값에 들어갈 list에 버스의 정류소 이름을 저장.
            busan_bus_list.append(busan_busStop['nodeid']) # dict의 value 값에 들어갈 list에 버스 정류소 ID을 저장.
            isOkay = True # 다음 버스 정류소를 알아내기 위해 True 조건 추가.
            isFind = True # 정류장을 1개라도 찾으면 True 변경.
        i += 1

    if isFind == False: # 정류장을 1개라도 찾지 못했으면,
        await ctx.channel.send("찾으시는 정류장이 없는 것 같아요...")
        bot.remove_command('정류장')

    else:
        @bot.command()
        async def 정류장(ctx, busan_busStopId):
            try:
                embed_2=discord.Embed(title="찾으시는 정류장의 정보예요!", timestamp=datetime.datetime.now(pytz.timezone('UTC')))
                embed_2.add_field
                # 원하는 키 값을 찾기 위해 사용.
                busan_target_key = list(busan_bus_dict.keys())[int(busan_busStopId) - 1]
                # 키 값을 이용해 원하는 정류소 1개의 정류소 ID.
                busan_select_busStopId = busan_bus_dict.get(busan_target_key)[1]
                # 3. 원하는 정류소 ID -> 실시간 정류소 정보 구하기.
                url = 'https://apis.data.go.kr/6260000/BusanBIMS/stopArrByBstopid'
                params = {'serviceKey' : 'cVAZvoe8Fo5X0jPOaI/iwkosf10ajo5M1M9smdhCik7esMTsHmgEnJnz+wNPRiQYj8uCB4TDtMsE9OBXu79gsA==',
                        'bstopid' : busan_select_busStopId
                        }
                busan_busStopId_response = requests.get(url, params=params)
                busan_busStopId_contents = busan_busStopId_response.text
                busan_busStopId_jsonStr = json.dumps(xmltodict.parse(busan_busStopId_contents))
                busan_busStopId_dict = json.loads(busan_busStopId_jsonStr)
                for busan_wantBusStop in busan_busStopId_dict['response']['body']['items']['item']:
                    # 원하는 딕셔너리 번호의 정보가 있으면,
                    if busan_busNo_input == busan_wantBusStop['lineno']:
                        embed_2.add_field(name="버스 정류장 이름",value=f"{busan_wantBusStop['nodenm']}", inline=False)
                        embed_2.add_field(name="다음 정류장", value=f"{busan_bus_dict.get(busan_target_key)[2]}", inline=False)
                        embed_2.add_field(name="버스 번호", value=f"{busan_wantBusStop['lineno']}", inline=False)
                        embed_2.add_field(name="버스 종류", value=f"{busan_wantBusStop['bustype']}", inline=False)
                        try:
                            embed_2.add_field(name="도착 예정 시간", value=f"{busan_wantBusStop['min1']} 분 남음", inline=False)
                            embed_2.add_field(name="남은 정류장 수", value=f"{busan_wantBusStop['station1']} 개 남음", inline=False)
                            await ctx.channel.send(embed=embed_2)
                            bot.remove_command('정류장')
                        except:
                            await ctx.channel.send("현재 버스가 운행 대기 중이예요...")
                            bot.remove_command('정류장')
            except:
                await ctx.channel.send("정류장 번호를 잘못 입력하신 것 같아요...")
            
        await ctx.channel.send(embed=embed)



# 나머지 입력 시.
@bot.event
async def on_command_error(ctx, error):
    if isinstance(error, commands.CommandNotFound):
        await ctx.channel.send("무슨소리인지 잘 모르겠어요...")
        return
    else:
        traceback.print_exception(
            type(error), error, error.__traceback__, file=sys.stderr)




# 봇 토큰 입력.
bot.run('YOUR TOKEN HERE')
