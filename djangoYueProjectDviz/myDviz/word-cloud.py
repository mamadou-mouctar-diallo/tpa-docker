from matplotlib import pylab as plt
from pymongo import MongoClient
from wordcloud import WordCloud
import numpy as np
from PIL import Image

def get_img(field, targetImageSrc, resImageSrc):
    client = MongoClient('mongodb://localhost:27017/')
    db = client['db_car']
    collection = db['colCatalogue']
    data = collection.find({}, {field: 1, "_id": 0})

    text = ''
    for i in data:
        value = i.get(field) + ' '  # Access the value of the specified field
        if value and isinstance(i.get(field), str):
            text += value  # Concatenate values to form the text

    data_cut = text.split()
    unique_words = set(data_cut)
    string = ' '.join(unique_words)
    # img
    img = Image.open(targetImageSrc)
    img_arr = np.array(img)
    wd = WordCloud(
        font_path='STHUPO.TTF',
        mask=img_arr,
        background_color='#04122c'
    )
    wd.generate_from_text(string)

    # draw img
    fig = plt.figure(0)
    plt.imshow(wd)
    plt.axis('off')
    plt.savefig(resImageSrc, dpi=800, bbox_inches='tight', pad_inches=-0.1)
get_img('marque', './../big-screen-vue-datav-master/public/carCloud.png',
        './../big-screen-vue-datav-master/public/car_Cloud.png')
