import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("./lib/dynamic.csv")

for i, (name, data) in enumerate(df.groupby('Algorithm')):
    ax = plt.subplot(1, 5, i+1)
    ax.yaxis.set_minor_formatter(plt.FormatStrFormatter('%d'))
    plt.ticklabel_format(style="sci", scilimits=(0,0), axis="y", useMathText=True)
    pivot = (data.plot(title = name, xlabel="Input size", ylabel="Number of comparisons", 
                       x = 'Size', y = 'Comparisons', legend=False, ax = ax))
plt.suptitle("Experiment 2 Plots")
plt.show()