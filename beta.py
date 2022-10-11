import traceback
import re
import sys
import os

import discord
from discord.ext import commands, tasks
import random

bot = commands.Bot(command_prefix='!', intents = discord.Intents.all())

# 봇 입장 시.
@bot.event
async def on_ready():
    print(f'Logged in as {bot.user} (ID: {bot.user.id})')
    game = discord.Game("딥러닝")
    await bot.change_presence(status=discord.Status.online, activity=game)



# help 명령어.
@bot.command()
async def 도움(ctx):
    embed=discord.Embed(title="도움말", color=0xffffff)
    embed.add_field(name="!도움", value="```도움말을 출력.```", inline=True)
    embed.add_field(name="!시트", value="```TRPG에서 사용하는 시트 불러오기.```", inline=True)
    embed.add_field(name="!ping", value="```pong을 출력.```", inline=True)
    embed.add_field(name="!임베드", value="```임베드 사용법을 출력.```", inline=True)
    embed.add_field(name="!안녕", value="```BeTa가 인사해줍니다.```", inline=True)
    embed.add_field(name="!roll", value="```1d100다이스를 굴립니다. 당신의 SAN치는?```", inline=True)
    embed.add_field(name="이모티콘 출력", value="```이모티콘을 사용하면 확대해서 보여줌.```", inline=True)
    await ctx.channel.send(embed=embed)



# TRPG 시트 불러오기.
@bot.command()
async def 시트(ctx):
    embed=discord.Embed(title="TRPG 캐릭터 모음 시트",
                        url="http://docs.google.com/spreadsheets/d/1TJdHVqnZQ1ST4XNqf9b7n_DY1Lidk0q5WjcuL5-usE8/edit?usp=drive_web&ouid=116231169099359786709", 
                        description="TRPG 캐릭터 시트가 모인 시트를 불러입니다.\n(클릭시 이동함.)", 
                        color=0xffffff)
    await ctx.channel.send(embed = embed)



# 임베드.
@bot.command()
async def 임베드(ctx):
    embed = discord.Embed(title="메인 제목", description="설명", color=0xfaf4cc)
    embed.set_footer(text="하단 설명")
    await ctx.channel.send(embed = embed)



# 핑퐁.
@bot.command()
async def ping(ctx):
    await ctx.channel.send('pong')



# hello.
@bot.command()
async def 안녕(ctx):
    embed=discord.Embed(title="안녕", color=0xffffff)
    await ctx.channel.send(embed=embed)



# 주사위 roll.
@bot.command()
async def roll(ctx):
    await ctx.send("주사위를 굴립니다.")
    a = random.randrange(1, 101)
    embed=discord.Embed(title="1d100의 결과는?", color=0xffffff)
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
        await message.channel.send(embed=embed)
        await message.delete()



# 나머지 입력 시.
@bot.event
async def on_command_error(ctx, error):
    if isinstance(error, commands.CommandNotFound):
        await ctx.channel.send("닥쳐")
        return
    else:
        traceback.print_exception(type(error), error, error.__traceback__, file=sys.stderr)





bot.run('Token')