out_file = None
mod_name = "lobotomycorp"
block_name = "grid_normal"

def gen_model_block_item():
    path = f"src\\main\\resources\\assets\\{mod_name}\\models\\item\\{block_name}.json"
    with open(path, "w") as out_file:
        out_file.write('{\n')
        content = "\t\"parent\": \"%s:block/%s\"\n"%(mod_name,block_name)
        out_file.write(content)
        out_file.write('}\n')

def gen_model_block():
    path = "src\\main\\resources\\assets\\%s\\models\\block\\%s.json"%(mod_name, block_name)
    with open(path, "w") as out_file:
        out_file.write('{\n')
        out_file.write('\t\"parent\": \"block/cube_all\",\n')
        out_file.write('\t\"textures\": {\n')
        content = "\t\t\"all\": \"%s:blocks/%s\"\n"%(mod_name,block_name)
        out_file.write(content)
        out_file.write('\t}\n')
        out_file.write('}\n')

def gen_block_state():
    path = "src\\main\\resources\\assets\\%s\\blockstates\\%s.json"%(mod_name, block_name)
    with open(path, "w") as out_file:
        out_file.write('{\n')
        out_file.write('\t\"variants\": {\n')
        content = "\t\t\"normal\": { \"model\": \"%s:%s\" }\n"%(mod_name,block_name)
        out_file.write(content)
        out_file.write('\t}\n')
        out_file.write('}\n')

def gen_block(block_name):
    print("Creating:", block_name)
    gen_model_block_item()
    gen_model_block()
    gen_block_state()

def gen_item(type_name, item_name):
    print("Creating:", type_name, " ", item_name)
    path = "src\\main\\resources\\assets\\%s\\models\\item\\%s.json"%(mod_name, item_name)
    with open(path, "w") as out_file:
        content = '{"parent": "item/handheld","textures": {"layer0":"%s:items/%s/%s"}}\n'%(mod_name, type_name, item_name)
        out_file.write(content)


gen_item("weapon", input())