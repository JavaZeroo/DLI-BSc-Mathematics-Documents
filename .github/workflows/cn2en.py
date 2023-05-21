from pypinyin import lazy_pinyin
import pandas as pd
from pathlib import Path
import os
import zipfile
import time

def zip(file, filename):
  with zipfile.ZipFile(filename, 'w') as zfile:
    for foldername, subfolders, files in os.walk(file):
      zfile.write(foldername)
      for i in files:
        zfile.write(os.path.join(foldername, i))
    zfile.close()

def main():
    basedir = Path('.')
    release_body = 'release_body.md'
    df = {'课程名称':[], '文件':[]}
    with open(release_body, 'w') as f:
        year_month_day = time.strftime("%Y-%m-%d", time.localtime())
        f.write(f"# 根据课程目录生成的文件\n\n更新日期：{year_month_day}\n\n")
        
        for path in basedir.iterdir():
            # check path is a folder named y[a number]s[a number]
            if path.is_dir() and path.name not in ['.git', '.github']:
                # get folder name
                for sub_folder in path.iterdir():
                    if sub_folder.is_dir():
                        # if sub_folder contained Chinese convert it to pinyin
                        zip_name = f'{path.name}_'
                        for chars in lazy_pinyin(sub_folder.name):
                            zip_name += chars
                        zip_name += '.zip'
                        zip_name = zip_name.replace(' ','_')
                        df['课程名称'].append(sub_folder.name)
                        df['文件'].append(f'[{zip_name}](https://github.com/JavaZeroo/DLI-BSc-Mathematics-Documents/releases/download/working/{zip_name})')
                        # zip(sub_folder, zip_name)  
                        # f.write(f'{sub_folder.name}\n[{zip_name}](https://github.com/JavaZeroo/DLI-BSc-Mathematics-Documents/releases/download/working/{zip_name})\n\n')
        f.write(pd.DataFrame(df).to_markdown(index=False))
    pass

if __name__ == '__main__':
    main()