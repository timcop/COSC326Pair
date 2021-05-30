average_wait_fifs = []
average_turnover_fifs = []
average_wait_ud = []
average_turnover_ud = []
average_wait_sjf = []
average_turnover_sjf = []
for i = 5:5:200
    f = open(string("data/averages", i, ".txt"))
    lines = readlines(f);
    avg = (split(lines[1]))
    avg = [parse(Float64, x) for x in avg]
    for line in lines
        line = split(line)
        line = [parse(Float64, x) for x in line]
        avg = avg + line
    end
    avg = avg / length(lines)
    append!(average_wait_fifs, avg[1])
    append!(average_turnover_fifs, avg[2])
    append!(average_wait_ud, avg[3])
    append!(average_turnover_ud, avg[4])
    append!(average_wait_sjf, avg[5])
    append!(average_turnover_sjf, avg[6])
end

# f1 = open(string("data/averages", "10", ".txt"))
# lines = readlines(f1)
# avg = (split(lines[1]))
# avg = [parse(Float64, x) for x in avg]
# for line in lines[2:end]
#     line = split(line)
#     line = [parse(Float64, x) for x in line]
#     avg = avg + line
# end
# avg = avg / length(lines)
# append!(average_wait_fifs, avg[1])
# append!(average_turnover_fifs, avg[2])
# append!(average_wait_ud, avg[3])
# append!(average_turnover_ud, avg[4])
# append!(average_wait_sjf, avg[5])
# append!(average_turnover_sjf, avg[6])
